package fr.epita.mti.jee.domain.exceptions.participants.runner;

import fr.epita.mti.jee.domain.exceptions.participants.RegisteredAsAnotherRoleException;

public class RegisteredAsZombieException extends RegisteredAsAnotherRoleException {
    public RegisteredAsZombieException() {
        super("Coureur", "Zombie");
    }
}
