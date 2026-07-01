package fr.epita.mti.jee.domain.repository;

import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    boolean existsWithUserName(Utilisateur user);

    void register(Utilisateur user);

    Set<Utilisateur> getAll();

    Optional<Utilisateur> get(Utilisateur userName);

    void license(Utilisateur userName);

    void unlicense(Utilisateur userName);
}
