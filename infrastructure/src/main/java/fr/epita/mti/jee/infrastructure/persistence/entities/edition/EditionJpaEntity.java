package fr.epita.mti.jee.infrastructure.persistence.entities.edition;

import fr.epita.mti.jee.domain.models.commun.Heure;
import fr.epita.mti.jee.domain.models.commun.PlageHoraire;
import fr.epita.mti.jee.domain.models.edition.CapaciteMax;
import fr.epita.mti.jee.domain.models.edition.Edition;
import fr.epita.mti.jee.domain.models.edition.Participants;
import fr.epita.mti.jee.infrastructure.persistence.entities.EmbeddableTimeSlot;
import fr.epita.mti.jee.infrastructure.persistence.entities.RunnerJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.entities.ZombieJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Entity
@Table(name = "editions")
public class EditionJpaEntity {
    @Id
    private String name;

    @Column(name = "edition_date")
    private LocalDate date;

    @Embedded
    private EmbeddableTimeSlot timeSlot;

    private String location;

    @Embedded
    private EmbeddableParticipants participants;

    private boolean cancelled;

    public EditionJpaEntity() {
    }

    public EditionJpaEntity(
        String name,
        LocalDate date,
        EmbeddableTimeSlot timeSlot,
        String location,
        EmbeddableParticipants participants,
        boolean cancelled
    ) {
        this.name         = name;
        this.date         = date;
        this.timeSlot     = timeSlot;
        this.location     = location;
        this.participants = participants;
        this.cancelled    = cancelled;
    }

    public String getName() {
        return name;
    }

    public EmbeddableParticipants getParticipants() {
        return participants;
    }

    public Edition toDomain() {
        return new Edition(
            name,
            date,
            new PlageHoraire(new Heure(timeSlot.startHour()), new Heure(timeSlot.endHour())),
            location,
            new Participants(
                new CapaciteMax(
                    participants.maxCapacity().runners(),
                    participants.maxCapacity().zombies()
                ),
                participants
                    .zombies()
                    .stream()
                    .map(ZombieJpaEntity::toDomain)
                    .collect(Collectors.toSet()),
                participants
                    .runners()
                    .stream()
                    .map(RunnerJpaEntity::toDomain)
                    .collect(Collectors.toSet())
            ),
            cancelled
        );
    }

    public Edition toDomainInParticipantsDomain() {
        return new Edition(
            name,
            date,
            new PlageHoraire(new Heure(timeSlot.startHour()), new Heure(timeSlot.endHour())),
            location,
            new Participants(
                new CapaciteMax(
                    participants.maxCapacity().runners(),
                    participants.maxCapacity().zombies()
                ),
                null,
                null
            ),
            cancelled
        );
    }
}
