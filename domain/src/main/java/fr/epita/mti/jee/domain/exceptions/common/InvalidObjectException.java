package fr.epita.mti.jee.domain.exceptions.common;

public class InvalidObjectException extends RuntimeException {
    public InvalidObjectException(String message) {
        super(message);
    }
}
