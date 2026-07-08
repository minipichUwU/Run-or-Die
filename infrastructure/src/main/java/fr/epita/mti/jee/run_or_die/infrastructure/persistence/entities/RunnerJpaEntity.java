package fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities;

import fr.epita.mti.jee.run_or_die.domain.models.coureur.Coureur;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "runners")
public class RunnerJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edition_id", insertable = false, updatable = false)
    EditionJpaEntity edition;

    public RunnerJpaEntity() {
    }

    public RunnerJpaEntity(
        Long id,
        UserJpaEntity user,
        EditionJpaEntity edition
    ) {
        this.id      = id;
        this.user    = user;
        this.edition = edition;
    }

    public Coureur toDomain() {
        return new Coureur(
            id,
            user.toDomain(),
            edition.toDomainInParticipantsDomain()
        );
    }
}
