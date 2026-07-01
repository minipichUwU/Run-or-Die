package fr.epita.mti.jee.infrastructure.persistence.repository.user;


import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.domain.repository.UserRepository;
import fr.epita.mti.jee.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    UserRepositoryJPA userRepositoryJPA;
    PasswordEncoder   passwordEncoder;

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
            .findAll()
            .stream()
            .map(UserJpaEntity::toDomain)
            .collect(Collectors.toSet());
    }

    @Override
    public Optional<Utilisateur> get(Utilisateur userName) {
        return userRepositoryJPA
            .findByUsername(userName.username())
            .stream()
            .map(UserJpaEntity::toDomain)
            .findFirst();
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
