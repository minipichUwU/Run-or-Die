package fr.epita.mti.jee.domain.exception.edition;

public class EditionNotFoundException extends InvalidEditionException {
    public EditionNotFoundException() {
        super("cette édition n'existe pas.");
    }
}
