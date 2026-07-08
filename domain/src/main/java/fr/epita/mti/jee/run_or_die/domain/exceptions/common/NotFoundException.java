package fr.epita.mti.jee.run_or_die.domain.exceptions.common;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
