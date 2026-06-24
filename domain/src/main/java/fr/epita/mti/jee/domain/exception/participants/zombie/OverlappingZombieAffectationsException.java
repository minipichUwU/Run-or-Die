package fr.epita.mti.jee.domain.exception.participants.zombie;

import fr.epita.mti.jee.domain.exception.participants.InvalidRegistrationException;

public class OverlappingZombieAffectationsException extends InvalidRegistrationException {
    public OverlappingZombieAffectationsException() {
        super("Un Zombie ne peut pas s'affecter plusieurs fois sur la même plage horaire.");
    }
}
