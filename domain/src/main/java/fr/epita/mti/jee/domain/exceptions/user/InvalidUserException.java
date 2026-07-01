package fr.epita.mti.jee.domain.exceptions.user;

import fr.epita.mti.jee.domain.exceptions.common.InvalidObjectException;

public class InvalidUserException extends InvalidObjectException {
    public InvalidUserException(String message) {
        super("Utilisateur invalid : " + message);
    }
}
