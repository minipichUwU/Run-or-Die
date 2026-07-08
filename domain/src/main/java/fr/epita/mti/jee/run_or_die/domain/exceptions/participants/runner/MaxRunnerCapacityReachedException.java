package fr.epita.mti.jee.run_or_die.domain.exceptions.participants.runner;

import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.MaxCapacityReachedException;

public class MaxRunnerCapacityReachedException extends MaxCapacityReachedException {
    public MaxRunnerCapacityReachedException() {
        super("Coureurs", ".");
    }
}
