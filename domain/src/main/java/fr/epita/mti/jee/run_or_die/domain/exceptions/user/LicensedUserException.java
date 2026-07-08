package fr.epita.mti.jee.run_or_die.domain.exceptions.user;

public class LicensedUserException extends InvalidUserException {
    public LicensedUserException() {
        super("Un utilisateur ne peut se faire licencié que s'il ne l'est pas déjà.");
    }
}
