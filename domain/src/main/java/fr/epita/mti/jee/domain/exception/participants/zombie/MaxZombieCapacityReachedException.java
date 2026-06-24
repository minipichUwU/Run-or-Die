package fr.epita.mti.jee.domain.exception.participants.zombie;

import fr.epita.mti.jee.domain.exception.participants.MaxCapacityReachedException;
import fr.epita.mti.jee.domain.model.common.PlageHoraire;

public class MaxZombieCapacityReachedException extends MaxCapacityReachedException {
    public MaxZombieCapacityReachedException(PlageHoraire timeSlot) {
        super("Zombies", " pendant au moins une heure, " + timeSlot + ".");
    }
}
