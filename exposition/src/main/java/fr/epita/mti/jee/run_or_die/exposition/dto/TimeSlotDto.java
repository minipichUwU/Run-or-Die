package fr.epita.mti.jee.run_or_die.exposition.dto;

import fr.epita.mti.jee.run_or_die.domain.models.commun.PlageHoraire;

public record TimeSlotDto(
    int startingHour,
    int endingHour
) {
    public TimeSlotDto(PlageHoraire plageHoraire) {
        this(
            plageHoraire.start().hour(),
            plageHoraire.end().hour()
        );
    }
}
