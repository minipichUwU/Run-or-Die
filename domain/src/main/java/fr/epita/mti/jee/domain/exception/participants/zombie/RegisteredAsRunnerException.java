package fr.epita.mti.jee.domain.exception.participants.zombie;

import fr.epita.mti.jee.domain.exception.participants.RegisteredAsAnotherRoleException;

public class RegisteredAsRunnerException extends RegisteredAsAnotherRoleException {
    public RegisteredAsRunnerException() {
        super("Zombie", "Coureur");
    }
}
