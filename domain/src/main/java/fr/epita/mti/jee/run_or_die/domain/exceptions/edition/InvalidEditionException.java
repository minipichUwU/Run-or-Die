package fr.epita.mti.jee.run_or_die.domain.exceptions.edition;

import fr.epita.mti.jee.run_or_die.domain.exceptions.common.InvalidObjectException;

public class InvalidEditionException extends InvalidObjectException {
    public InvalidEditionException(String message) {
        super("Edition Invalide : " + message);
    }
}
