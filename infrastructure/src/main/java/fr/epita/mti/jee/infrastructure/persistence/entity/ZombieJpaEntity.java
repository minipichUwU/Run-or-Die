package fr.epita.mti.jee.infrastructure.persistence.entity;

import fr.epita.mti.jee.domain.model.zombie.Zombie;
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
@Table(name = "zombie")
public class ZombieJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edition_id", insertable = false, updatable = false)
    EditionJpaEntity edition;

    @Embedded
    EmbeddableTimeSlot timeSlot;

    public ZombieJpaEntity() {
    }

    public ZombieJpaEntity(
        Long id,
        String email,
        EditionJpaEntity edition,
        EmbeddableTimeSlot timeSlot
    ) {
        this.id       = id;
        this.email    = email;
        this.edition  = edition;
        this.timeSlot = timeSlot;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public EditionJpaEntity getEdition() {
        return edition;
    }

    public EmbeddableTimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Zombie toDomain() {
        return new Zombie(
            id,
            email,
            edition.toDomainWithoutParticipantsLists(),
            timeSlot.toDomain()
        );
    }
}
