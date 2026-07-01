package fr.epita.mti.jee.domain.exceptions.edition;

import fr.epita.mti.jee.domain.exceptions.common.InvalidObjectException;

public class InvalidEditionException extends InvalidObjectException {
    public InvalidEditionException(String message) {
        super("Edition Invalide : " + message);
    }
}
