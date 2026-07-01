package fr.epita.mti.jee.domain.exceptions.plage_horaire;

import fr.epita.mti.jee.domain.exceptions.common.InvalidObjectException;

public class InvalidTimeSlotException extends InvalidObjectException {
    public InvalidTimeSlotException() {
        super(
            "Plage Horaire Invalide : L'heure de fin doit être après l'heure de début. (et donc doit durer au minimum 1 heure)");
    }

    public InvalidTimeSlotException(String message) {
        super("Plage Horaire Invalide : " + message);
    }
}
