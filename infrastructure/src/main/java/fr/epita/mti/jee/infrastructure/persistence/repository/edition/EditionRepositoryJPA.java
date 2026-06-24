package fr.epita.mti.jee.infrastructure.persistence.repository.edition;

import fr.epita.mti.jee.infrastructure.persistence.entity.EditionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EditionRepositoryJPA extends JpaRepository<EditionJpaEntity, String> {

    Optional<EditionJpaEntity> findByName(String name);

    boolean existsByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE EditionJpaEntity e SET e.cancelled = TRUE WHERE e.name = ?1")
    void cancelByName(String name);

    // chatgpt ne pourrait même pas espérer atteindre ce niveau de dédication.
    boolean existsByDateAndTimeSlotStartHourGreaterThanEqualAndTimeSlotStartHourLessThanOrDateAndTimeSlotEndHourGreaterThanAndTimeSlotEndHourLessThanEqualOrDateAndTimeSlotStartHourLessThanEqualAndDateAndTimeSlotEndHourGreaterThanEqual(
        LocalDate date, int timeSlotStart, int timeSlotEnd,
        LocalDate date2, int timeSlotStart2, int timeSlotEnd2,
        LocalDate date3, int timeSlotStart3,
        LocalDate date4, int timeSlotEnd3
    );

    default boolean hasTimeSlotConflict(
        LocalDate date,
        int startingHour,
        int endingHour
    ) {
        return existsByDateAndTimeSlotStartHourGreaterThanEqualAndTimeSlotStartHourLessThanOrDateAndTimeSlotEndHourGreaterThanAndTimeSlotEndHourLessThanEqualOrDateAndTimeSlotStartHourLessThanEqualAndDateAndTimeSlotEndHourGreaterThanEqual(
            date, startingHour, endingHour,
            date, startingHour, endingHour,
            date, startingHour,
            date, endingHour
        );
    }

    List<EditionJpaEntity> findByDateGreaterThanAndCancelledFalse(LocalDate date);
}
