package fr.epita.mti.jee.exposition.dto.edition;

import java.util.List;

public record EditionParticipantsDto(
    List<EditionZombieDto> zombies,
    List<EditionRunnerDto> runners
) {}
