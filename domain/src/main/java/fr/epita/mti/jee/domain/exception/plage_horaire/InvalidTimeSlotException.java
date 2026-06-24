package fr.epita.mti.jee.domain.exception.plage_horaire;

public class InvalidTimeSlotException extends RuntimeException {
    public InvalidTimeSlotException() {
        super(
            "Plage Horaire Invalide : L'heure de fin doit être après l'heure de début. (et donc doit durer au minimum 1 heure)");
    }

    public InvalidTimeSlotException(String message) {
        super("Plage Horaire Invalide : " + message);
    }
}
