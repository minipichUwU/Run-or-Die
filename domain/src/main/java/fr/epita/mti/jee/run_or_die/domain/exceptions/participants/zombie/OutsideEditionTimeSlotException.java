package fr.epita.mti.jee.run_or_die.domain.exceptions.participants.zombie;

import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.InvalidRegistrationException;

public class OutsideEditionTimeSlotException extends InvalidRegistrationException {
    public OutsideEditionTimeSlotException() {
        super("Un zombie ne peut s'affecter qu'à des plages horaires incluses dans une édition.");
    }
}
