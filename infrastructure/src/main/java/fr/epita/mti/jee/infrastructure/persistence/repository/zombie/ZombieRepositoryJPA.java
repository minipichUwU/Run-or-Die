package fr.epita.mti.jee.infrastructure.persistence.repository.zombie;

import fr.epita.mti.jee.infrastructure.persistence.entity.EditionJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.entity.EmbeddableTimeSlot;
import fr.epita.mti.jee.infrastructure.persistence.entity.ZombieJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface ZombieRepositoryJPA extends JpaRepository<ZombieJpaEntity, Long> {
    List<ZombieJpaEntity> findByEmail(String email);

    Set<ZombieJpaEntity> findByEmailAndEdition(String email, EditionJpaEntity edition);

    @Transactional
    @Modifying
    @Query("update ZombieJpaEntity z set z.timeSlot = ?1 where z.id = ?2")
    void updateTimeSlotById(EmbeddableTimeSlot timeSlot, Long id);
}
