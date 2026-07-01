package fr.epita.mti.jee.infrastructure.persistence.repository.edition;

import fr.epita.mti.jee.infrastructure.persistence.entities.edition.EditionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface EditionRepositoryJPA extends JpaRepository<EditionJpaEntity, String> {

    Optional<EditionJpaEntity> findByName(String name);

    boolean existsByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE EditionJpaEntity e " +
           "SET e.cancelled = TRUE " +
           "WHERE e.name = ?1")
    void cancelByName(String name);

    @Query("SELECT (count(edition) > 0) FROM EditionJpaEntity edition " +
           "WHERE edition.date = ?1 " +
           "AND edition.timeSlot.startHour < ?3 " +
           "AND ?2 < edition.timeSlot.endHour")
    boolean hasEditionOverlapping(
        LocalDate date,
        int startHour,
        int endHour
    );

    Set<EditionJpaEntity> findByCancelledFalseAndDateGreaterThan(LocalDate date);

    @Query("SELECT edition FROM EditionJpaEntity edition " +
           "JOIN RunnerJpaEntity runner ON edition = runner.edition " +
           "WHERE runner.user.username = ?1")
    Set<EditionJpaEntity> getByRegisteredUser(String username);
}
