package fr.epita.mti.jee.infrastructure.persistence.entity;

import fr.epita.mti.jee.domain.model.common.Heure;
import fr.epita.mti.jee.domain.model.common.PlageHoraire;
import fr.epita.mti.jee.domain.model.edition.CapaciteMax;
import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.domain.model.edition.Participants;
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

    public LocalDate getDate() {
        return date;
    }

    public EmbeddableTimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getLocation() {
        return location;
    }

    public EmbeddableParticipants getParticipants() {
        return participants;
    }

    public boolean isCancelled() {
        return cancelled;
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
                    .collect(Collectors.toSet())
            ),
            isCancelled()
        );
    }

    public Edition toDomainWithoutParticipantsLists() {
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
                null
            ),
            isCancelled()
        );
    }
}
