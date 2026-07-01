package fr.epita.mti.jee.domain.exceptions.common;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
