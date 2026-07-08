package fr.epita.mti.jee.run_or_die.domain.infrastructure_services;

import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;

public interface SurvivorsFederationService {
    boolean isLicensed(Utilisateur user);

    void askToLicense(Utilisateur user);

    void askToUnlicense(Utilisateur user);
}
