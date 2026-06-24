package fr.epita.mti.jee.infrastructure.persistence.repository;


import fr.epita.mti.jee.domain.model.utilisateur.Utilisateur;
import fr.epita.mti.jee.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    JdbcUserDetailsManager jdbcUserDetailsManager;

    PasswordEncoder passwordEncoder;

    public UserRepositoryImpl(
        JdbcUserDetailsManager jdbcUserDetailsManager,
        PasswordEncoder passwordEncoder
    ) {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder        = passwordEncoder;
    }

    @Override
    public void register(Utilisateur user) {
        User.UserBuilder userBuilder = User.builder()
            .passwordEncoder(pwd -> passwordEncoder.encode(pwd));

        UserDetails userDetails = userBuilder
            .username(user.email())
            .password(user.motDePasse())
            .roles("UTILISATEUR")
            .build();

        jdbcUserDetailsManager.createUser(userDetails);
    }
}
