package fr.epita.mti.jee.run_or_die.infrastructure.services;

import fr.epita.mti.jee.run_or_die.domain.infrastructure_services.SurvivorsFederationService;
import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.run_or_die.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurvivorsFederationServiceImpl implements SurvivorsFederationService {

    private final UserRepository userRepository;

    @Autowired
    public SurvivorsFederationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isLicensed(Utilisateur user) {
        System.out.println("requête à \"la fédération des survivants\": t'as licencié " +
                           user.username() +
                           " ?");
        System.out.println("réponse de la \"la fédération des survivants\": *ronfle*");

        return userRepository.get(user).isLicensed();
    }

    @Override
    public void askToLicense(Utilisateur user) {
        System.out.println("requête à \"la fédération des survivants\": licencie " +
                           user.username() +
                           ".");
        System.out.println("réponse de la \"la fédération des survivants\": OK.");

        userRepository.license(user);
    }

    @Override
    public void askToUnlicense(Utilisateur user) {
        System.out.println("requête à \"la fédération des survivants\": retire la licence de " +
                           user.username() +
                           ".");
        System.out.println("réponse de la \"la fédération des survivants\": OK.");

        userRepository.unlicense(user);
    }
}
