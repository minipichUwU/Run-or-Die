package fr.epita.mti.jee.application.service;

import fr.epita.mti.jee.domain.exception.edition.EditionNotFoundException;
import fr.epita.mti.jee.domain.infrastructureService.MailingService;
import fr.epita.mti.jee.domain.model.common.Heure;
import fr.epita.mti.jee.domain.model.common.PlageHoraire;
import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.domain.model.zombie.Zombie;
import fr.epita.mti.jee.domain.repository.EditionRepository;
import fr.epita.mti.jee.domain.repository.ZombieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ZombieService {
    ZombieRepository  zombieRepository;
    EditionRepository editionRepository;

    MailingService mailingService;

    @Autowired
    public ZombieService(
        ZombieRepository zombieRepository,
        EditionRepository editionRepository,
        MailingService mailingService
    ) {
        this.zombieRepository  = zombieRepository;
        this.editionRepository = editionRepository;
        this.mailingService    = mailingService;
    }

    private static boolean hasTimeSlotNotRegisteredIn(Zombie zombie, Edition edition) {
        Set<Zombie> selfZombies = edition
            .participants()
            .zombies()
            .stream()
            .filter(z -> z.email().equals(zombie.email()))
            .collect(Collectors.toSet());

        if (selfZombies.size() == 1 &&
            selfZombies
                .stream()
                .anyMatch(z -> z.plageHoraire().equals(edition.plageHoraire()))) {
            return true;
        }
        return false;
    }

    public void affectZombie(Zombie zombie) {
        Edition edition = editionRepository
            .getEdition(zombie.edition())
            .orElseThrow(EditionNotFoundException::new);

        Set<Zombie> zombies = zombieRepository.getByEmailInEdition(zombie.email(), edition);

        Optional<Zombie> predecessor = zombies
            .stream()
            .filter(z -> z.plageHoraire().fin().equals(zombie.plageHoraire().debut()))
            .findFirst();

        Optional<Zombie> successor = zombies
            .stream()
            .filter(z -> z.plageHoraire().debut().equals(zombie.plageHoraire().fin()))
            .findFirst();

        if (predecessor.isPresent() || successor.isPresent()) {
            mergeZombieTimeSlot(predecessor, successor, zombie, edition);
        } else {
            createZombie(zombie, edition);
        }
    }

    private void mergeZombieTimeSlot(
        Optional<Zombie> predecessor,
        Optional<Zombie> successor,
        Zombie zombie,
        Edition edition
    ) {
        Heure start = predecessor
            .map(z -> z.plageHoraire().debut())
            .orElse(zombie.plageHoraire().debut());
        Heure end = successor
            .map(z -> z.plageHoraire().fin())
            .orElse(zombie.plageHoraire().fin());

        List<Long> surroundingIds = new ArrayList<>();
        predecessor.ifPresent(z -> surroundingIds.add(z.id()));
        successor.ifPresent(z -> surroundingIds.add(z.id()));

        if (predecessor.isPresent() && successor.isPresent()) {
            zombieRepository.delete(successor.get());
        }

        zombieRepository.updateTimeSlot(Zombie.updateTimeSlot(
            surroundingIds,
            zombie.email(),
            edition,
            new PlageHoraire(start, end)
        ));
    }

    private void createZombie(Zombie zombie, Edition edition) {
        editionRepository.register(zombieRepository.create(Zombie.createNew(
            zombie.email(),
            edition,
            zombie.plageHoraire()
        )));
    }

    public List<Zombie> getZombies(Zombie zombie) {
        return zombieRepository.getByEmail(zombie.email());
    }

    public List<Edition> getRegisterableRuns(Zombie zombie) {
        List<Edition> activeEditions = editionRepository.getActive();

        return activeEditions
            .stream()
            .filter(edition -> {
                if (hasTimeSlotNotRegisteredIn(zombie, edition)) {return false;}

                int h = edition.plageHoraire().debut().heure();
                for (; h <= edition.plageHoraire().fin().heure(); h++) {
                    int hour = h;
                    if (edition
                            .participants()
                            .zombies()
                            .stream()
                            .filter(z -> z.plageHoraire().contains(hour))
                            .count() < edition.participants().capaciteMax().zombies()) {
                        break;
                    }
                }
                return h <= edition.plageHoraire().fin().heure();
            })
            .toList();
    }

    public Map<Integer, Long> getZombiesHistogram(Edition edition) {
        Edition editionEntity = editionRepository
            .getEdition(edition)
            .orElseThrow(EditionNotFoundException::new);

        Map<Integer, Long> histogram = new HashMap<>();

        for (int h = editionEntity.plageHoraire().debut().heure();
             h < editionEntity.plageHoraire().fin().heure();
             h++) {
            int hour = h;
            long zombiesNumber = editionEntity
                .participants()
                .zombies()
                .stream()
                .filter(z -> z.plageHoraire().contains(hour))
                .count();

            histogram.put(hour, zombiesNumber);
        }

        return histogram;
    }
}
