package fr.epita.mti.jee.run_or_die.domain.exceptions.user;

import fr.epita.mti.jee.run_or_die.domain.exceptions.common.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("cet utilisateur n'existe pas.");
    }
}
