package fr.epita.mti.jee.run_or_die.exposition.dto.edition.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import fr.epita.mti.jee.run_or_die.exposition.dto.edition.EditionParticipantsDto;
import fr.epita.mti.jee.run_or_die.exposition.dto.edition.EditionRunnerDto;
import fr.epita.mti.jee.run_or_die.exposition.dto.edition.EditionZombieDto;
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
    long maxRunners,
    long maxZombies,
    EditionParticipantsDto participants,
    boolean cancelled

) {
    public EditionResponse(Edition domainEdition) {
        this(
            domainEdition.name(),
            domainEdition.date(),
            domainEdition.timeSlot().start().hour(),
            domainEdition.timeSlot().end().hour(),
            domainEdition.location(),
            domainEdition.participants().maxCapacity().runners(),
            domainEdition.participants().maxCapacity().zombies(),
            new EditionParticipantsDto(
                domainEdition
                    .participants()
                    .zombies()
                    .stream()
                    .map(EditionZombieDto::new)
                    .toList(),
                domainEdition.participants().runners().stream().map(EditionRunnerDto::new).toList()
            ),
            domainEdition.isCancelled()
        );
    }
}
