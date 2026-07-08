package fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities.embeddable;

import fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities.RunnerJpaEntity;
import fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities.ZombieJpaEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Embeddable
public record EmbeddableParticipants(
    @Embedded
    EmbeddableMaxCapacity maxCapacity,

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "edition_id", nullable = false)
    Set<ZombieJpaEntity> zombies,

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "edition_id", nullable = false)
    Set<RunnerJpaEntity> runners
) {}
