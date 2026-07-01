package fr.epita.mti.jee.domain.exceptions.edition;

import fr.epita.mti.jee.domain.exceptions.common.NotFoundException;

public class EditionNotFoundException extends NotFoundException {
    public EditionNotFoundException() {
        super("cette édition n'existe pas.");
    }
}
