package fr.epita.mti.jee.domain.exception.participants.runner;

import fr.epita.mti.jee.domain.exception.participants.MaxCapacityReachedException;

public class MaxRunnerCapacityReachedException extends MaxCapacityReachedException {
    public MaxRunnerCapacityReachedException() {
        super("Coureurs", ".");
    }
}
