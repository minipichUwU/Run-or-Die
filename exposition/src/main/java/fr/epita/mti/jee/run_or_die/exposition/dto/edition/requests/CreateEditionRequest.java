package fr.epita.mti.jee.run_or_die.exposition.dto.edition.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.epita.mti.jee.run_or_die.domain.models.commun.Heure;
import fr.epita.mti.jee.run_or_die.domain.models.commun.PlageHoraire;
import fr.epita.mti.jee.run_or_die.domain.models.edition.CapaciteMax;
import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record CreateEditionRequest(
    @Schema(example = "Run or Die — Édition Octobre 2027")
    String name,

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(example = "31-10-2027", format = "dd-MM-yyyy")
    LocalDate date,

    @Schema(example = "13")
    int startingHour,

    @Schema(example = "20")
    int endingHour,

    @Schema(example = "Antarctique du Nord")
    String location,

    @Schema(example = "200")
    int maxRunners,

    @Schema(example = "50")
    int maxZombies
) {
    public Edition createDomainEdition() {
        return new Edition(
            name,
            date,
            new PlageHoraire(new Heure(startingHour), new Heure(endingHour)),
            location,
            new CapaciteMax(maxRunners, maxZombies)
        );
    }
}
