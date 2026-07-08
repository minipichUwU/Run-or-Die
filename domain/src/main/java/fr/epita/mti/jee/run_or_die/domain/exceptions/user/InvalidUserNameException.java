package fr.epita.mti.jee.run_or_die.domain.exceptions.user;

public class InvalidUserNameException extends InvalidUserException {
    public InvalidUserNameException() {
        super("L'email est invalide.");
    }
}
