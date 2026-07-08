package fr.epita.mti.jee.run_or_die.exposition.dto.participant.runner;

import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import fr.epita.mti.jee.run_or_die.exposition.dto.participant.ParticipantEditionDto;

public record RunnerEditionAvailabilityDto(
    ParticipantEditionDto edition,
    long remainingCapacity
) {
    public RunnerEditionAvailabilityDto(Edition edition, long remainingCapacity) {
        this(new ParticipantEditionDto(edition), remainingCapacity);
    }
}
