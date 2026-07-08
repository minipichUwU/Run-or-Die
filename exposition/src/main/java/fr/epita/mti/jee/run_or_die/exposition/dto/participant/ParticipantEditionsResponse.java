package fr.epita.mti.jee.run_or_die.exposition.dto.participant;

import java.util.List;

public record ParticipantEditionsResponse(
    List<ParticipantEditionDto> editions
) {}
