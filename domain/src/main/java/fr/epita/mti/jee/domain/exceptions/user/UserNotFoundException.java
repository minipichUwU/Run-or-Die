package fr.epita.mti.jee.domain.exceptions.user;

import fr.epita.mti.jee.domain.exceptions.common.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("cet utilisateur n'existe pas.");
    }
}
