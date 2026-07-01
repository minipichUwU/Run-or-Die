package fr.epita.mti.jee.infrastructure.persistence.repository.runner;

import fr.epita.mti.jee.infrastructure.persistence.entities.RunnerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepositoryJpa extends JpaRepository<RunnerJpaEntity, Long> {}