package fr.epita.mti.jee.domain.exceptions.edition;

public class InvalidEditionDateException extends InvalidEditionException {
    public InvalidEditionDateException() {
        super("La date d'une édition doit être dans le futur.");
    }
}
