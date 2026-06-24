package fr.epita.mti.jee.domain.exception.user;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super("L'email est invalide");
    }
}
