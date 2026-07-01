package fr.epita.mti.jee.domain.exceptions.participants;

public class InvalidRegistrationDateException extends InvalidRegistrationException {
    public InvalidRegistrationDateException() {
        super("Un zombie/coureur ne peut s'affecter/s'incrire qu'à des éditions futures\n");
    }
}
