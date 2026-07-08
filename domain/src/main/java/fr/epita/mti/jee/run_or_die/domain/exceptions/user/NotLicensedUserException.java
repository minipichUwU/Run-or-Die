package fr.epita.mti.jee.run_or_die.domain.exceptions.user;

public class NotLicensedUserException extends InvalidUserException {
    public NotLicensedUserException() {
        super("Un utilisateur ne peut se perdre sa licence s'il n'en a pas.");
    }
}
