package fr.epita.mti.jee.domain.model.utilisateur;

import fr.epita.mti.jee.domain.exception.user.InvalidEmailException;

public record Utilisateur(
    String email,
    String motDePasse
) {
    public static Utilisateur createNew(String email, String motDePasse) {
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidEmailException();
        }

        return new Utilisateur(email.trim().toLowerCase(), motDePasse);
    }
}
