package fr.epita.mti.jee.application.service;

import fr.epita.mti.jee.domain.model.utilisateur.Utilisateur;
import fr.epita.mti.jee.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(Utilisateur utilisateur) {
        userRepository.register(utilisateur);
    }
}
