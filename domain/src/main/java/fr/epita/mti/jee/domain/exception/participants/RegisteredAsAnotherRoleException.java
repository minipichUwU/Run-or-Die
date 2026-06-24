package fr.epita.mti.jee.domain.exception.participants;

public class RegisteredAsAnotherRoleException extends InvalidRegistrationException {
    public RegisteredAsAnotherRoleException(String registerAs, String registeredAs) {
        super("Un " +
              registerAs +
              " ne peut pas s'affecter s'il est déjà inscrit en tant que " +
              registeredAs);
    }
}
