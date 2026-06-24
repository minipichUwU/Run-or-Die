package fr.epita.mti.jee.domain.repository;

import fr.epita.mti.jee.domain.model.utilisateur.Utilisateur;

public interface UserRepository {
    void register(Utilisateur user);
}
