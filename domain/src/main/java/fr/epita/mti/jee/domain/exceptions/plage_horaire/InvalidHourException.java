package fr.epita.mti.jee.domain.exceptions.plage_horaire;

public class InvalidHourException extends InvalidTimeSlotException {
    public InvalidHourException() {
        super("Les heures vont de 0 à 23 (inclus)");
    }
}
