package fr.epita.mti.jee.run_or_die.domain.models.utilisateur;

import fr.epita.mti.jee.run_or_die.domain.exceptions.user.InvalidUserNameException;

import java.util.Objects;

public final class Utilisateur {
    private final String  email;
    private final String  motDePasse;
    private final boolean estLicencie;

    public Utilisateur(
        String username,
        String password,
        boolean licensed
    ) {
        this.email       = username;
        this.motDePasse  = password;
        this.estLicencie = licensed;
    }

    public Utilisateur(String username) {
        this(username, null, false);
    }

    public static Utilisateur createNew(String username, String password) {
        if (!username.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidUserNameException();
        }

        return new Utilisateur(username.trim().toLowerCase(), password, false);
    }

    public String username() {
        return email;
    }

    public String password() {
        return motDePasse;
    }

    public boolean isLicensed() {
        return estLicencie;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null || obj.getClass() != this.getClass()) {return false;}
        var that = (Utilisateur) obj;
        return Objects.equals(this.email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, motDePasse, estLicencie);
    }

    @Override
    public String toString() {
        return "Utilisateur[" +
               "email=" + email + ", " +
               "estLicencie=" + estLicencie + ']';
    }
}
