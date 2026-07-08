package fr.epita.mti.jee.run_or_die.exposition.dto.edition;

import java.util.List;

public record EditionParticipantsDto(
    List<EditionZombieDto> zombies,
    List<EditionRunnerDto> runners
) {}
