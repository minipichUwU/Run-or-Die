package fr.epita.mti.jee.infrastructure.persistence.entities.edition;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record EmbeddableMaxCapacity(
    @Column(name = "max_runners")
    long runners,
    
    @Column(name = "max_zombies")
    long zombies
) {}
