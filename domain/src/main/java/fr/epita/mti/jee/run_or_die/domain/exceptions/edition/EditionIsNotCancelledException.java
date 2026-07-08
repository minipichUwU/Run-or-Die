package fr.epita.mti.jee.run_or_die.domain.exceptions.edition;

public class EditionIsNotCancelledException extends InvalidEditionException {
    public EditionIsNotCancelledException() {
        super("L'édition n'est pas encore annulé.");
    }
}
