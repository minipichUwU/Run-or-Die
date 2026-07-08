package fr.epita.mti.jee.run_or_die.domain.exceptions.participants.runner;

import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.InvalidRegistrationException;

public class AlreadyRegisteredException extends InvalidRegistrationException {
    public AlreadyRegisteredException() {
        super("Un coureur ne peut pas s'inscrire deux fois à la même édition.");
    }
}
