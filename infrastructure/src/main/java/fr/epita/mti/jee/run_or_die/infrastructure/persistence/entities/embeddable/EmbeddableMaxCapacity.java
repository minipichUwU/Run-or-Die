package fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record EmbeddableMaxCapacity(
    @Column(name = "max_runners")
    long runners,

    @Column(name = "max_zombies")
    long zombies
) {}
