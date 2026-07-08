package fr.epita.mti.jee.run_or_die.domain.exceptions.edition;

public class InvalidEditionDateException extends InvalidEditionException {
    public InvalidEditionDateException() {
        super("La date d'une édition doit être dans le futur.");
    }
}
