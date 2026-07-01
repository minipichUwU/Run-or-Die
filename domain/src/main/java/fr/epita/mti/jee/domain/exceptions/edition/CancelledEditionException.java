package fr.epita.mti.jee.domain.exceptions.edition;

import fr.epita.mti.jee.domain.exceptions.common.InvalidObjectException;

public class CancelledEditionException extends InvalidObjectException {
    public CancelledEditionException(String consequence) {
        super(consequence + " : L'édition est annulée");
    }
}
