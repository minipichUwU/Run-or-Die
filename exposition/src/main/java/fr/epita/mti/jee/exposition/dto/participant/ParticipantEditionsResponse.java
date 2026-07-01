package fr.epita.mti.jee.exposition.dto.participant;

import java.util.List;

public record ParticipantEditionsResponse(
    List<ParticipantEditionDto> editions
) {}
