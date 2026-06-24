package fr.epita.mti.jee.exposition.dto.editions.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.epita.mti.jee.domain.model.edition.Edition;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record EditionResponse(
    String name,

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(pattern = "dd-MM-yyyy")
    LocalDate date,

    int startingHour,
    int endingHour,
    String location,
    int maxRunners,
    int maxZombies,
    ParticipantsDto participants,
    boolean cancelled

) {
    public EditionResponse(Edition domainEdition) {
        this(
            domainEdition.nom(),
            domainEdition.date(),
            domainEdition.plageHoraire().debut().heure(),
            domainEdition.plageHoraire().fin().heure(),
            domainEdition.lieu(),
            domainEdition.participants().capaciteMax().coureurs(),
            domainEdition.participants().capaciteMax().zombies(),
            new ParticipantsDto(domainEdition
                .participants()
                .zombies()
                .stream()
                .map(EditionZombieDto::new)
                .toList()),
            domainEdition.estAnnulee()
        );
    }
}
