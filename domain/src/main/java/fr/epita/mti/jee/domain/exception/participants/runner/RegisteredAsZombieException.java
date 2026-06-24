package fr.epita.mti.jee.domain.exception.participants.runner;

import fr.epita.mti.jee.domain.exception.participants.RegisteredAsAnotherRoleException;

public class RegisteredAsZombieException extends RegisteredAsAnotherRoleException {
    public RegisteredAsZombieException() {
        super("Coureur", "Zombie");
    }
}
