package fr.epita.mti.jee.infrastructure.persistence.repository.edition;

import fr.epita.mti.jee.domain.models.coureur.Coureur;
import fr.epita.mti.jee.domain.models.edition.Edition;
import fr.epita.mti.jee.domain.models.zombie.Zombie;
import fr.epita.mti.jee.domain.repository.EditionRepository;
import fr.epita.mti.jee.infrastructure.persistence.entities.EmbeddableTimeSlot;
import fr.epita.mti.jee.infrastructure.persistence.entities.edition.EditionJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.entities.edition.EmbeddableMaxCapacity;
import fr.epita.mti.jee.infrastructure.persistence.entities.edition.EmbeddableParticipants;
import fr.epita.mti.jee.infrastructure.persistence.repository.runner.RunnerRepositoryJpa;
import fr.epita.mti.jee.infrastructure.persistence.repository.zombie.ZombieRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class EditionRepositoryImpl implements EditionRepository {

    private final EditionRepositoryJPA editionRepositoryJPA;
    private final ZombieRepositoryJPA  zombieRepositoryJPA;
    private final RunnerRepositoryJpa  runnerRepositoryJPA;

    @Autowired
    public EditionRepositoryImpl(
        EditionRepositoryJPA editionRepositoryJPA,
        ZombieRepositoryJPA zombieRepositoryJPA,
        RunnerRepositoryJpa runnerRepositoryJPA
    ) {
        this.editionRepositoryJPA = editionRepositoryJPA;
        this.zombieRepositoryJPA  = zombieRepositoryJPA;
        this.runnerRepositoryJPA  = runnerRepositoryJPA;
    }

    @Override
    public void organize(Edition domainEdition) {
        editionRepositoryJPA.save(this.toEntity(domainEdition));
    }

    @Override
    public Set<Edition> getAllEditions() {
        return editionRepositoryJPA
            .findAll()
            .stream()
            .map(EditionJpaEntity::toDomain)
            .collect(Collectors.toSet());
    }

    @Override
    public Optional<Edition> get(Edition domainEditionName) {
        return editionRepositoryJPA
            .findByName(domainEditionName.name())
            .stream()
            .map(EditionJpaEntity::toDomain)
            .findFirst();
    }

    @Override
    public Set<Edition> getActiveEditions() {
        return editionRepositoryJPA
            .findByCancelledFalseAndDateGreaterThan(LocalDate.now())
            .stream()
            .map(EditionJpaEntity::toDomain)
            .collect(Collectors.toSet());
    }

    @Override
    public boolean existsEditionWithName(String name) {
        return editionRepositoryJPA.existsByName(name);
    }

    @Override
    public boolean doOverlapAnotherEdition(Edition domainEdition) {
        return editionRepositoryJPA.hasEditionOverlapping(
            domainEdition.date(),
            domainEdition.timeSlot().start().hour(),
            domainEdition.timeSlot().end().hour()
        );
    }

    @Override
    public void delete(Edition domainEditionName) {
        editionRepositoryJPA.delete(toEntity(domainEditionName));
    }

    @Override
    public void cancel(Edition domainEditionName) {
        editionRepositoryJPA.cancelByName(domainEditionName.name());
    }

    @Override
    public void affect(Zombie zombie) {
        EditionJpaEntity editionJPA = editionRepositoryJPA
            .findByName(zombie.edition().name())
            .get();
        editionJPA
            .getParticipants()
            .zombies()
            .add(zombieRepositoryJPA.getReferenceById(zombie.id()));
        editionRepositoryJPA.save(editionJPA);
    }

    @Override
    public void register(Coureur runner) {
        EditionJpaEntity editionJPA = editionRepositoryJPA
            .findByName(runner.edition().name())
            .get();

        editionJPA
            .getParticipants()
            .runners()
            .add(runnerRepositoryJPA.getReferenceById(runner.id()));
        editionRepositoryJPA.save(editionJPA);
    }

    @Override
    public Set<Edition> getEditionWithRegisteredRunner(Coureur runnerName) {
        return editionRepositoryJPA
            .getByRegisteredUser(runnerName.user().username())
            .stream()
            .map(EditionJpaEntity::toDomain)
            .collect(Collectors.toSet());
    }

    private EditionJpaEntity toEntity(Edition edition) {
        return new EditionJpaEntity(
            edition.name(),
            edition.date(),
            new EmbeddableTimeSlot(
                edition.timeSlot().start().hour(),
                edition.timeSlot().end().hour()
            ),
            edition.location(),
            new EmbeddableParticipants(
                new EmbeddableMaxCapacity(
                    edition.participants().maxCapacity().runners(),
                    edition.participants().maxCapacity().zombies()
                ),
                edition
                    .participants()
                    .zombies()
                    .stream()
                    .map(zombie -> zombieRepositoryJPA.getReferenceById(zombie.id()))
                    .collect(Collectors.toSet()),
                edition
                    .participants()
                    .runners()
                    .stream()
                    .map(runner -> runnerRepositoryJPA.getReferenceById(runner.id()))
                    .collect(Collectors.toSet())
            ),
            edition.isCancelled()
        );
    }
}
