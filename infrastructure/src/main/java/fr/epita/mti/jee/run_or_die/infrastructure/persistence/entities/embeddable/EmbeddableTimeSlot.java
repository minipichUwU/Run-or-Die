package fr.epita.mti.jee.run_or_die.infrastructure.persistence.entities.embeddable;

import fr.epita.mti.jee.run_or_die.domain.models.commun.Heure;
import fr.epita.mti.jee.run_or_die.domain.models.commun.PlageHoraire;
import jakarta.persistence.Embeddable;

@Embeddable
public record EmbeddableTimeSlot(
    int startHour,
    int endHour
) {
    public EmbeddableTimeSlot(PlageHoraire domainTimeSlot) {
        this(domainTimeSlot.start().hour(), domainTimeSlot.end().hour());
    }

    public PlageHoraire toDomain() {
        return new PlageHoraire(new Heure(startHour), new Heure(endHour));
    }
}
