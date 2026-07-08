package fr.epita.mti.jee.run_or_die.application.services;

import fr.epita.mti.jee.run_or_die.domain.exceptions.user.LicensedUserException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.user.NotLicensedUserException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.user.UserNameExistsException;
import fr.epita.mti.jee.run_or_die.domain.infrastructure_services.SurvivorsFederationService;
import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.run_or_die.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final SurvivorsFederationService survivorsFederationService;

    @Autowired
    public UserService(
        UserRepository userRepository,
        SurvivorsFederationService survivorsFederationService
    ) {
        this.userRepository             = userRepository;
        this.survivorsFederationService = survivorsFederationService;
    }

    public void createUser(Utilisateur user) {
        if (userRepository.existsWithUserName(user)) {
            throw new UserNameExistsException(user.username());
        }

        userRepository.register(user);
    }

    public Utilisateur getUser(Utilisateur userName) {
        return userRepository.get(userName);
    }

    public Set<Utilisateur> getAllUsers() {
        return userRepository.getAll();
    }

    public boolean isLicensed(Utilisateur userName) {
        return survivorsFederationService.isLicensed(userName);
    }

    public void licenseUser(Utilisateur userName) {
        boolean isUserLicensed = isLicensed(userName);

        if (isUserLicensed) {
            throw new LicensedUserException();
        }

        survivorsFederationService.askToLicense(userName);
    }

    public void unlicenseUser(Utilisateur userName) {
        boolean isUserLicensed = isLicensed(userName);

        if (!isUserLicensed) {
            throw new NotLicensedUserException();
        }

        survivorsFederationService.askToUnlicense(userName);
    }
}
