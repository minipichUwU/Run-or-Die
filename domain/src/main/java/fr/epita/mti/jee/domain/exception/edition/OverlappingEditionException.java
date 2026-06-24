package fr.epita.mti.jee.domain.exception.edition;

public class OverlappingEditionException extends InvalidEditionException {
    public OverlappingEditionException() {
        super("Deux éditions ne peuvent pas se chevaucher dans le temps.");
    }
}
