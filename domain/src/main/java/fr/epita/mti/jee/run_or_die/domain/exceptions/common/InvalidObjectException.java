package fr.epita.mti.jee.run_or_die.domain.exceptions.common;

public class InvalidObjectException extends RuntimeException {
    public InvalidObjectException(String message) {
        super(message);
    }
}
