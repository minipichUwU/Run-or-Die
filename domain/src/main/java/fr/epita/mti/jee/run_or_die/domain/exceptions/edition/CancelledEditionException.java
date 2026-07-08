package fr.epita.mti.jee.run_or_die.domain.exceptions.edition;

import fr.epita.mti.jee.run_or_die.domain.exceptions.common.InvalidObjectException;

public class CancelledEditionException extends InvalidObjectException {
    public CancelledEditionException(String consequence) {
        super(consequence + " : L'édition est annulée.");
    }
}
