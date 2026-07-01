package fr.epita.mti.jee.domain.exceptions.participants.zombie;

import fr.epita.mti.jee.domain.exceptions.participants.InvalidRegistrationException;

public class OutsideEditionTimeSlotException extends InvalidRegistrationException {
    public OutsideEditionTimeSlotException() {
        super("Un zombie ne peut s'affecter qu'à des plages horaires incluses dans une édition \n");
    }
}
