package fr.epita.mti.jee.domain.model.edition;

import fr.epita.mti.jee.domain.model.zombie.Zombie;

import java.util.Set;

public record Participants(
    CapaciteMax capaciteMax,
    Set<Zombie> zombies
) {}
