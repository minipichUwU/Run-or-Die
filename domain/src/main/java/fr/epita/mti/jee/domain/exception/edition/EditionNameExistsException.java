package fr.epita.mti.jee.domain.exception.edition;

public class EditionNameExistsException extends InvalidEditionException {
    public EditionNameExistsException() {
        super("Le nom de l'édition est déjà pris");
    }
}
