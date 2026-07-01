package fr.epita.mti.jee.domain.exceptions.user;

public class InvalidUserNameException extends InvalidUserException {
    public InvalidUserNameException() {
        super("L'email est invalide");
    }
}
