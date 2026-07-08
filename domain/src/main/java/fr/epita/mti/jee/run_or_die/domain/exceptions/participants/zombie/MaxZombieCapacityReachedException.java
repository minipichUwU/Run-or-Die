package fr.epita.mti.jee.run_or_die.domain.exceptions.participants.zombie;

import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.MaxCapacityReachedException;

public class MaxZombieCapacityReachedException extends MaxCapacityReachedException {
    public MaxZombieCapacityReachedException() {
        super("Zombies", " pendant au moins une heure dans la plage horaire donnée.");
    }
}
