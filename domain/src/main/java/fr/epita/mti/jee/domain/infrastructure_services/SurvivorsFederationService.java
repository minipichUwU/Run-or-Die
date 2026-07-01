package fr.epita.mti.jee.domain.infrastructure_services;

import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;

public interface SurvivorsFederationService {
    boolean isLicensed(Utilisateur user);
}
