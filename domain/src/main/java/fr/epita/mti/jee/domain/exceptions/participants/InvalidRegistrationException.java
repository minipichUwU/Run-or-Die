package fr.epita.mti.jee.domain.exceptions.participants;

import fr.epita.mti.jee.domain.exceptions.common.InvalidObjectException;

public class InvalidRegistrationException extends InvalidObjectException {
    public InvalidRegistrationException(String message) {
        super("Participation refusée : " + message);
    }
}
