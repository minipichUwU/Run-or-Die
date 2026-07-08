package fr.epita.mti.jee.run_or_die.domain.exceptions.edition;

public class OverlappingEditionException extends InvalidEditionException {
    public OverlappingEditionException() {
        super("Deux éditions ne peuvent pas se chevaucher dans le temps.");
    }
}
