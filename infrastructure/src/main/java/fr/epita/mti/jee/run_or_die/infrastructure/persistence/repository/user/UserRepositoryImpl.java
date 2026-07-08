package fr.epita.mti.jee.run_or_die.infrastructure.persistence.repository.user;


import fr.epita.mti.jee.run_or_die.domain.exceptions.user.UserNotFoundException;
import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.run_or_die.domain.repository.UserRepository;
import fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJPA userRepositoryJPA;
    private final PasswordEncoder   passwordEncoder;

    @Autowired
    public UserRepositoryImpl(
        UserRepositoryJPA userRepositoryJPA,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.passwordEncoder   = passwordEncoder;
    }

    @Override
    public boolean existsWithUserName(Utilisateur user) {
        return userRepositoryJPA.existsByUsername(user.username());
    }

    @Override
    public void register(Utilisateur user) {
        userRepositoryJPA.save(new UserJpaEntity(
            user.username(),
            passwordEncoder.encode(user.password()),
            "USER",
            false
        ));
    }

    @Override
    public Set<Utilisateur> getAll() {
        return userRepositoryJPA
            .findByRoleIs("USER")
            .stream()
            .map(UserJpaEntity::toDomain)
            .collect(Collectors.toSet());
    }

    @Override
    public Utilisateur get(Utilisateur userName) {
        return userRepositoryJPA
            .findByUsernameIgnoreCase(userName.username())
            .stream()
            .map(UserJpaEntity::toDomain)
            .findFirst()
            .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void license(Utilisateur userName) {
        userRepositoryJPA.licenseUser(userName.username());
    }

    @Override
    public void unlicense(Utilisateur userName) {
        userRepositoryJPA.unlicenseUser(userName.username());
    }
}
