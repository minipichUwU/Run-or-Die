package fr.epita.mti.jee.domain.exceptions.participants.runner;

import fr.epita.mti.jee.domain.exceptions.participants.MaxCapacityReachedException;

public class MaxRunnerCapacityReachedException extends MaxCapacityReachedException {
    public MaxRunnerCapacityReachedException() {
        super("Coureurs", ".");
    }
}
