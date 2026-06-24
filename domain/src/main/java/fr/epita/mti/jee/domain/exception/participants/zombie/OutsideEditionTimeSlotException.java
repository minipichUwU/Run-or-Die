package fr.epita.mti.jee.domain.exception.participants.zombie;

import fr.epita.mti.jee.domain.exception.participants.InvalidRegistrationException;

public class OutsideEditionTimeSlotException extends InvalidRegistrationException {
    public OutsideEditionTimeSlotException() {
        super("Un zombie ne peut s'affecter qu'à des plages horaires incluses dans une édition \n");
    }
}
