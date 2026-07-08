package fr.epita.mti.jee.run_or_die.domain.repository;

import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;

import java.util.Set;

public interface UserRepository {
    boolean existsWithUserName(Utilisateur user);

    void register(Utilisateur user);

    Set<Utilisateur> getAll();

    Utilisateur get(Utilisateur userName);

    void license(Utilisateur userName);

    void unlicense(Utilisateur userName);
}
