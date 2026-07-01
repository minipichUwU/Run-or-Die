package fr.epita.mti.jee.application.services;

import fr.epita.mti.jee.domain.exceptions.edition.EditionNotFoundException;
import fr.epita.mti.jee.domain.exceptions.participants.runner.NotLicensedException;
import fr.epita.mti.jee.domain.exceptions.user.UserNotFoundException;
import fr.epita.mti.jee.domain.infrastructure_services.SurvivorsFederationService;
import fr.epita.mti.jee.domain.models.coureur.Coureur;
import fr.epita.mti.jee.domain.models.edition.Edition;
import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.domain.repository.EditionRepository;
import fr.epita.mti.jee.domain.repository.RunnerRepository;
import fr.epita.mti.jee.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RunnerService {

    RunnerRepository  runnerRepository;
    EditionRepository editionRepository;
    UserRepository    userRepository;

    SurvivorsFederationService survivorsFederationService;

    @Autowired
    public RunnerService(
        RunnerRepository runnerRepository,
        EditionRepository editionRepository,
        UserRepository userRepository,
        SurvivorsFederationService survivorsFederationService
    ) {
        this.runnerRepository           = runnerRepository;
        this.editionRepository          = editionRepository;
        this.userRepository             = userRepository;
        this.survivorsFederationService = survivorsFederationService;
    }

    public void registerRunner(Coureur runner) {
        Edition edition = editionRepository
            .get(runner.edition())
            .orElseThrow(EditionNotFoundException::new);

        Utilisateur user = userRepository
            .get(runner.user())
            .orElseThrow(UserNotFoundException::new);

        if (!(survivorsFederationService.isLicensed(runner.user()) ||
              user.isIllegallyLicensed())) {
            throw new NotLicensedException();
        }

        editionRepository.register(
            runnerRepository.create(
                Coureur.createNew(
                    user,
                    edition
                )));
    }

    public Set<Edition> getRegisteredRuns(Coureur runnerName) {
        return editionRepository.getEditionWithRegisteredRunner(runnerName);
    }

    public Set<Edition> getRegisterableRuns(Coureur runnerName) {
        Set<Edition> activeEditions = editionRepository.getActiveEditions();

        return activeEditions
            .stream()
            .filter(edition -> {
                if (edition
                    .participants()
                    .zombies()
                    .stream()
                    .anyMatch(zombie -> Objects.equals(zombie.user(), runnerName.user()))
                ) {
                    return false;
                }

                Set<Coureur> runners = edition.participants().runners();

                if (runners
                    .stream()
                    .anyMatch(runner -> Objects.equals(runner.user(), runnerName.user()))
                ) {
                    return false;
                }

                return runners.size() < edition.participants().maxCapacity().runners();
            })
            .collect(Collectors.toSet());
    }

    public Map<Edition, Long> getEditionsRemainingCapacity() {
        Set<Edition> activeEditions = editionRepository.getActiveEditions();

        return activeEditions.stream().collect(Collectors.toMap(
            edition -> edition,
            edition -> edition.participants().maxCapacity().runners() -
                       edition.participants().runners().size()
        ));
    }
}
