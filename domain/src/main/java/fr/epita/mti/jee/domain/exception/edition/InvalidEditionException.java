package fr.epita.mti.jee.domain.exception.edition;

public class InvalidEditionException extends RuntimeException {
    public InvalidEditionException(String message) {
        super("Edition Invalide : " + message);
    }
}
