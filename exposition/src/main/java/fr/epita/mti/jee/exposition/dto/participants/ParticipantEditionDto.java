package fr.epita.mti.jee.exposition.dto.participants;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.epita.mti.jee.domain.model.edition.Edition;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record ParticipantEditionDto(
    String name,

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(pattern = "dd-MM-yyyy")
    LocalDate date,

    int startingHour,
    int endingHour,
    String location,
    int maxRunners,
    int maxZombies
) {
    public ParticipantEditionDto(Edition edition) {
        this(
            edition.nom(),
            edition.date(),
            edition.plageHoraire().debut().heure(),
            edition.plageHoraire().fin().heure(),
            edition.lieu(),
            edition.participants().capaciteMax().coureurs(),
            edition.participants().capaciteMax().zombies()
        );
    }
}
