package fr.epita.mti.jee.infrastructure.services;

import fr.epita.mti.jee.domain.infrastructure_services.SurvivorsFederationService;
import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public class SurvivorsFederationServiceImpl implements SurvivorsFederationService {
    @Override
    public boolean isLicensed(Utilisateur user) {
        System.out.println("requête à \"la fédération des survivants\": t'as licensié " +
                           user.username() +
                           " ?");
        System.out.println("réponse de la \"la fédération des survivants\": *ronfle*");
        return false;
    }
}
