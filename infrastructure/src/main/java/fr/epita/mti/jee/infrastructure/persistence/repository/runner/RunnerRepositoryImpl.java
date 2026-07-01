package fr.epita.mti.jee.infrastructure.persistence.repository.runner;

import fr.epita.mti.jee.domain.models.coureur.Coureur;
import fr.epita.mti.jee.domain.repository.RunnerRepository;
import fr.epita.mti.jee.infrastructure.persistence.entities.RunnerJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.repository.edition.EditionRepositoryJPA;
import fr.epita.mti.jee.infrastructure.persistence.repository.user.UserRepositoryJPA;
import org.springframework.stereotype.Repository;

@Repository
public class RunnerRepositoryImpl implements RunnerRepository {

    private final RunnerRepositoryJpa  runnerRepositoryJPA;
    private final UserRepositoryJPA    userRepositoryJPA;
    private final EditionRepositoryJPA editionRepositoryJPA;

    public RunnerRepositoryImpl(
        RunnerRepositoryJpa runnerRepositoryJPA,
        UserRepositoryJPA userRepositoryJPA,
        EditionRepositoryJPA editionRepositoryJPA
    ) {
        this.runnerRepositoryJPA  = runnerRepositoryJPA;
        this.userRepositoryJPA    = userRepositoryJPA;
        this.editionRepositoryJPA = editionRepositoryJPA;
    }

    @Override
    public Coureur create(Coureur runner) {
        return runnerRepositoryJPA.save(toEntity(runner)).toDomain();
    }

    private RunnerJpaEntity toEntity(Coureur runner) {
        return new RunnerJpaEntity(
            runner.id(),
            userRepositoryJPA.getReferenceById(runner.user().username()),
            editionRepositoryJPA.getReferenceById(runner.edition().name())
        );
    }
}
