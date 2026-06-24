package fr.epita.mti.jee.domain.exception.edition;

public class EditionIsNotCancelledException extends InvalidEditionException {
    public EditionIsNotCancelledException() {
        super("L'édition n'est pas encore annulé.");
    }
}
