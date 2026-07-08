package fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities;

import fr.epita.mti.jee.run_or_die.domain.models.zombie.Zombie;
import fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities.embeddable.EmbeddableTimeSlot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "zombies")
public class ZombieJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edition_id", insertable = false, updatable = false)
    EditionJpaEntity edition;

    @Embedded
    EmbeddableTimeSlot timeSlot;

    public ZombieJpaEntity() {
    }

    public ZombieJpaEntity(
        Long id,
        UserJpaEntity user,
        EditionJpaEntity edition,
        EmbeddableTimeSlot timeSlot
    ) {
        this.id       = id;
        this.user     = user;
        this.edition  = edition;
        this.timeSlot = timeSlot;
    }

    public Zombie toDomain() {
        return new Zombie(
            id,
            user.toDomain(),
            edition.toDomainInParticipantsDomain(),
            timeSlot.toDomain()
        );
    }
}
