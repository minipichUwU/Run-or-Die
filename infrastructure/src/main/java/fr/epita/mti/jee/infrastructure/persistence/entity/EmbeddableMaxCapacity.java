package fr.epita.mti.jee.infrastructure.persistence.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public record EmbeddableMaxCapacity(
    int runners,
    int zombies
) {}
