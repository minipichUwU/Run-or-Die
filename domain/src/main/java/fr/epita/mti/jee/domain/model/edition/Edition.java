package fr.epita.mti.jee.domain.model.edition;

import fr.epita.mti.jee.domain.exception.edition.InvalidEditionDateException;
import fr.epita.mti.jee.domain.model.common.PlageHoraire;

import java.time.LocalDate;
import java.util.HashSet;

public record Edition(
    String nom,
    LocalDate date,
    PlageHoraire plageHoraire,
    String lieu,
    Participants participants,
    boolean estAnnulee
) {
    public Edition(
        String name,
        LocalDate date,
        PlageHoraire timeSlot,
        String location,
        CapaciteMax capaciteMax,
        boolean cancelled
    ) {
        this(name, date, timeSlot, location, new Participants(capaciteMax, null), cancelled);
    }

    public Edition(String editionName) {
        this(
            editionName,
            null,
            null,
            null,
            (Participants) null,
            false
        );
    }

    public static Edition createNew(
        String name,
        LocalDate date,
        PlageHoraire timeSlot,
        String location,
        CapaciteMax capaciteMax
    ) {
        if (date.isBefore(LocalDate.now().plusDays(1))) {
            throw new InvalidEditionDateException();
        }

        return new Edition(
            name,
            date,
            timeSlot,
            location,
            new Participants(capaciteMax, new HashSet<>()),
            false
        );
    }
}
