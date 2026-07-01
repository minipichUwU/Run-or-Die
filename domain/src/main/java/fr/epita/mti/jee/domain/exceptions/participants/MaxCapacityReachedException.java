package fr.epita.mti.jee.domain.exceptions.participants;

public class MaxCapacityReachedException extends InvalidRegistrationException {
    public MaxCapacityReachedException(String registerAs, String message) {
        super("La capacité maximale des " + registerAs + " a déjà été atteinte" + message);
    }
}
