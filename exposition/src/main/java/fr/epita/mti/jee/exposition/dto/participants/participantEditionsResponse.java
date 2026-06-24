package fr.epita.mti.jee.exposition.dto.participants;

import java.util.List;

public record participantEditionsResponse(
    List<ParticipantEditionDto> editions
) {}
