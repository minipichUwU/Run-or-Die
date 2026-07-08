package fr.epita.mti.jee.run_or_die.domain.exceptions.edition;

public class EditionNameExistsException extends InvalidEditionException {
    public EditionNameExistsException() {
        super("Le nom de l'édition est déjà pris.");
    }
}
