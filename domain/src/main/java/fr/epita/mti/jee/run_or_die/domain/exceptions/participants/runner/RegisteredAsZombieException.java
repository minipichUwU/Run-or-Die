package fr.epita.mti.jee.run_or_die.domain.exceptions.participants.runner;

import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.RegisteredAsAnotherRoleException;

public class RegisteredAsZombieException extends RegisteredAsAnotherRoleException {
    public RegisteredAsZombieException() {
        super("Coureur", "Zombie");
    }
}
