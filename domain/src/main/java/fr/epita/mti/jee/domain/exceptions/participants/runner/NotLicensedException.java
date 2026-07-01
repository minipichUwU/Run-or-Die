package fr.epita.mti.jee.domain.exceptions.participants.runner;

import fr.epita.mti.jee.domain.exceptions.participants.InvalidRegistrationException;

public class NotLicensedException extends InvalidRegistrationException {
    public NotLicensedException() {
        super(
            "Pour toute inscription, le coureur doit être licencié auprès de \"la fédération des survivants\".");
    }
}
