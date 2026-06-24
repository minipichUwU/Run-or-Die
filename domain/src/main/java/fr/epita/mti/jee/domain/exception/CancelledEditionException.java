package fr.epita.mti.jee.domain.exception;

public class CancelledEditionException extends RuntimeException {
    public CancelledEditionException(String consequence) {
        super(consequence + " : L'édition est annulée");
    }
}
