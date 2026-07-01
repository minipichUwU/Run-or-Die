package fr.epita.mti.jee.domain.exceptions.participants.zombie;

import fr.epita.mti.jee.domain.exceptions.participants.RegisteredAsAnotherRoleException;

public class RegisteredAsRunnerException extends RegisteredAsAnotherRoleException {
    public RegisteredAsRunnerException() {
        super("Zombie", "Coureur");
    }
}
