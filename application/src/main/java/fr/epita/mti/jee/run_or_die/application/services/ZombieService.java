package fr.epita.mti.jee.run_or_die.application.services;

import fr.epita.mti.jee.run_or_die.domain.exceptions.edition.EditionNotFoundException;
import fr.epita.mti.jee.run_or_die.domain.models.commun.Heure;
import fr.epita.mti.jee.run_or_die.domain.models.commun.PlageHoraire;
import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import fr.epita.mti.jee.run_or_die.domain.models.zombie.Zombie;
import fr.epita.mti.jee.run_or_die.domain.repository.EditionRepository;
import fr.epita.mti.jee.run_or_die.domain.repository.ZombieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ZombieService {

    private final ZombieRepository  zombieRepository;
    private final EditionRepository editionRepository;

    @Autowired
    public ZombieService(
        ZombieRepository zombieRepository,
        EditionRepository editionRepository
    ) {
        this.zombieRepository  = zombieRepository;
        this.editionRepository = editionRepository;
    }

    public void affectZombie(Zombie zombie) {
        Edition edition = editionRepository
            .find(zombie.edition())
            .orElseThrow(EditionNotFoundException::new);

        Set<Zombie> zombies = zombieRepository.getWithSameUserInEdition(zombie);

        Optional<Zombie> predecessor = zombies
            .stream()
            .filter(z -> Objects.equals(z.timeSlot().end(), zombie.timeSlot().start()))
            .findFirst();

        Optional<Zombie> successor = zombies
            .stream()
            .filter(z -> Objects.equals(z.timeSlot().start(), zombie.timeSlot().end()))
            .findFirst();

        if (predecessor.isPresent() || successor.isPresent()) {
            mergeZombieTimeSlot(predecessor, successor, zombie, edition);
        } else {
            affectZombie(zombie, edition);
        }
    }

    private void mergeZombieTimeSlot(
        Optional<Zombie> predecessor,
        Optional<Zombie> successor,
        Zombie zombie,
        Edition edition
    ) {
        Heure start = predecessor.map(z -> z.timeSlot().start()).orElse(zombie.timeSlot().start());
        Heure end   = successor.map(z -> z.timeSlot().end()).orElse(zombie.timeSlot().end());

        List<Long> surroundingIds = new ArrayList<>();
        predecessor.ifPresent(z -> surroundingIds.add(z.id()));
        successor.ifPresent(z -> surroundingIds.add(z.id()));

        if (predecessor.isPresent() && successor.isPresent()) {
            zombieRepository.delete(successor.get());
        }

        zombieRepository.updateTimeSlot(
            Zombie.setNewTimeSlot(
                surroundingIds,
                zombie.user(),
                edition,
                new PlageHoraire(start, end)
            ));
    }

    private void affectZombie(Zombie zombie, Edition edition) {
        editionRepository.affect(
            zombieRepository.create(
                Zombie.createNew(
                    zombie.user(),
                    edition,
                    zombie.timeSlot()
                )));
    }

    public Set<Zombie> getAffectedTimeSlots(Zombie zombieName) {
        return zombieRepository.getWithSameUser(zombieName.user());
    }

    public Set<Edition> getAffectableRuns(Zombie zombieName) {
        Set<Edition> activeEditions = editionRepository.getActiveEditions();

        return activeEditions
            .stream()
            .filter(edition -> {
                if (edition.hasUserRegisteredAsRunner(zombieName.user())) {
                    return false;
                }

                Set<Zombie> zombies = edition.participants().zombies();

                Set<Integer> affectedHours = zombies
                    .stream()
                    .filter(z -> Objects.equals(z.user(), zombieName.user()))
                    .flatMapToInt(sz -> IntStream.range(
                        sz.timeSlot().start().hour(),
                        sz.timeSlot().end().hour()
                    ))
                    .boxed()
                    .collect(Collectors.toSet());

                Set<Integer> notAffectedHours = IntStream
                    .range(edition.timeSlot().start().hour(), edition.timeSlot().end().hour())
                    .filter(hour -> !affectedHours.contains(hour))
                    .boxed()
                    .collect(Collectors.toSet());

                if (notAffectedHours.isEmpty()) {
                    return false;
                }

                Map<Integer, Long> zombieHistogram = edition.getZombieHistogram();

                long maxCapacity = edition.participants().maxCapacity().zombies();

                return notAffectedHours
                    .stream()
                    .anyMatch(hour -> zombieHistogram.get(hour) < maxCapacity);
            })
            .collect(Collectors.toSet());
    }

    public Map<Integer, Long> getZombiesHistogram(Edition editionName) {
        Edition edition = editionRepository
            .find(editionName)
            .orElseThrow(EditionNotFoundException::new);

        return edition.getZombieHistogram();
    }
}
