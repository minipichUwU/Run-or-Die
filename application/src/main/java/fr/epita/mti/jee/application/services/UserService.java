package fr.epita.mti.jee.application.services;

import fr.epita.mti.jee.domain.exceptions.user.UserNameExistsException;
import fr.epita.mti.jee.domain.exceptions.user.UserNotFoundException;
import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(Utilisateur user) {
        if (userRepository.existsWithUserName(user)) {
            throw new UserNameExistsException(user.username());
        }

        userRepository.register(user);
    }

    public Utilisateur getUser(Utilisateur userName) {
        return userRepository.get(userName).orElseThrow(UserNotFoundException::new);
    }

    public Set<Utilisateur> getAllUsers() {
        return userRepository.getAll();
    }

    public boolean isIllegallyLicensed(Utilisateur userName) {
        return userRepository
            .get(userName)
            .orElseThrow(UserNotFoundException::new)
            .isIllegallyLicensed();
    }

    public void illegallyLicenseUser(Utilisateur userName) {
        userRepository.license(userName);
    }

    public void illegallyUnlicenseLicense(Utilisateur userName) {
        userRepository.unlicense(userName);
    }
}
