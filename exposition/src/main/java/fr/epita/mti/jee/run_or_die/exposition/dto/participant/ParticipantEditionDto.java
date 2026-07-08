package fr.epita.mti.jee.run_or_die.exposition.dto.participant;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
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
    long maxRunners,
    long maxZombies
) {
    public ParticipantEditionDto(Edition edition) {
        this(
            edition.name(),
            edition.date(),
            edition.timeSlot().start().hour(),
            edition.timeSlot().end().hour(),
            edition.location(),
            edition.participants().maxCapacity().runners(),
            edition.participants().maxCapacity().zombies()
        );
    }
}
