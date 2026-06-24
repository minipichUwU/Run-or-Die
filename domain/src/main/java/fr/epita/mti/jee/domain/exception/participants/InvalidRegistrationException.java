package fr.epita.mti.jee.domain.exception.participants;

public class InvalidRegistrationException extends RuntimeException {
    public InvalidRegistrationException(String message) {
        super("Participation refusée : " + message);
    }
}
