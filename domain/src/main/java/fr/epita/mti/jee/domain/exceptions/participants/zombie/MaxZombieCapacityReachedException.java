package fr.epita.mti.jee.domain.exceptions.participants.zombie;

import fr.epita.mti.jee.domain.exceptions.participants.MaxCapacityReachedException;

public class MaxZombieCapacityReachedException extends MaxCapacityReachedException {
    public MaxZombieCapacityReachedException() {
        super("Zombies", " pendant au moins une heure dans la plage horaire donnée.");
    }
}
