package fr.epita.mti.jee.run_or_die.domain.exceptions.user;

import fr.epita.mti.jee.run_or_die.domain.exceptions.common.InvalidObjectException;

public class InvalidUserException extends InvalidObjectException {
    public InvalidUserException(String message) {
        super("Utilisateur invalide : " + message);
    }
}
