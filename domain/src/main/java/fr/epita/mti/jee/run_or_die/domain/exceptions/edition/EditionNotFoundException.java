package fr.epita.mti.jee.run_or_die.domain.exceptions.edition;

import fr.epita.mti.jee.run_or_die.domain.exceptions.common.NotFoundException;

public class EditionNotFoundException extends NotFoundException {
    public EditionNotFoundException() {
        super("cette édition n'existe pas.");
    }
}
