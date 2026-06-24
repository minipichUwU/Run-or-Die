package fr.epita.mti.jee.exposition.dto;

import fr.epita.mti.jee.domain.model.common.PlageHoraire;

public record TimeSlotResponse(
    int startingHour,
    int endingHour
) {
    public TimeSlotResponse(PlageHoraire plageHoraire) {
        this(
            plageHoraire.debut().heure(),
            plageHoraire.fin().heure()
        );
    }
}
