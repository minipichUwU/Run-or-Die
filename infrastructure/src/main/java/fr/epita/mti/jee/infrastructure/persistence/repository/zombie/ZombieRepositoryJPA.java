package fr.epita.mti.jee.infrastructure.persistence.repository.zombie;

import fr.epita.mti.jee.infrastructure.persistence.entities.EmbeddableTimeSlot;
import fr.epita.mti.jee.infrastructure.persistence.entities.UserJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.entities.ZombieJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.entities.edition.EditionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface ZombieRepositoryJPA extends JpaRepository<ZombieJpaEntity, Long> {
    Set<ZombieJpaEntity> findByUser(UserJpaEntity user);

    Set<ZombieJpaEntity> findByUserAndEdition(UserJpaEntity user, EditionJpaEntity edition);

    @Transactional
    @Modifying
    @Query("update ZombieJpaEntity z set z.timeSlot = ?1 where z.id = ?2")
    void updateTimeSlotById(EmbeddableTimeSlot timeSlot, Long id);
}
