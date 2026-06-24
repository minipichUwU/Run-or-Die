package fr.epita.mti.jee.infrastructure.persistence.entity;

import fr.epita.mti.jee.domain.model.common.Heure;
import fr.epita.mti.jee.domain.model.common.PlageHoraire;
import jakarta.persistence.Embeddable;

@Embeddable
public record EmbeddableTimeSlot(
    int startHour,
    int endHour
) {
    public EmbeddableTimeSlot(PlageHoraire domainTimeSlot) {
        this(domainTimeSlot.debut().heure(), domainTimeSlot.fin().heure());
    }

    public PlageHoraire toDomain() {
        return new PlageHoraire(new Heure(startHour), new Heure(endHour));
    }
}
