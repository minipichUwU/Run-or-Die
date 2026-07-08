package fr.epita.mti.jee.run_or_die.domain.exceptions.participants.zombie;

import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.RegisteredAsAnotherRoleException;

public class RegisteredAsRunnerException extends RegisteredAsAnotherRoleException {
    public RegisteredAsRunnerException() {
        super("Zombie", "Coureur");
    }
}
