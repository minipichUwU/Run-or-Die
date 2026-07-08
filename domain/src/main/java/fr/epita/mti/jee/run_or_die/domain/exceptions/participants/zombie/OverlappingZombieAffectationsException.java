package fr.epita.mti.jee.run_or_die.domain.exceptions.participants.zombie;

import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.InvalidRegistrationException;

public class OverlappingZombieAffectationsException extends InvalidRegistrationException {
    public OverlappingZombieAffectationsException() {
        super("Un Zombie ne peut pas s'affecter plusieurs fois sur la même plage horaire.");
    }
}
