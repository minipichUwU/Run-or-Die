package fr.epita.mti.jee.run_or_die.infrastructure.persistence.repository.user;

import fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

public interface UserRepositoryJPA extends JpaRepository<UserJpaEntity, String> {

    boolean existsByUsername(String username);

    Optional<UserJpaEntity> findByUsernameIgnoreCase(String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserJpaEntity u SET u.licensed = TRUE WHERE u.username = ?1")
    void licenseUser(String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserJpaEntity u SET u.licensed = FALSE WHERE u.username = ?1")
    void unlicenseUser(String username);

    Set<UserJpaEntity> findByRoleIs(String role);
}
