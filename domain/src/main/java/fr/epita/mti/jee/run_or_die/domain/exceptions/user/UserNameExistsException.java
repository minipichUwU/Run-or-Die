package fr.epita.mti.jee.run_or_die.domain.exceptions.user;

public class UserNameExistsException extends InvalidUserException {
    public UserNameExistsException(String username) {
        super("Cet email est déjà utilisé (" + username + ").");
    }
}
