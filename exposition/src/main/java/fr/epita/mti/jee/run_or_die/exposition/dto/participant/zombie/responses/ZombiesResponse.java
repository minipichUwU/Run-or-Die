package fr.epita.mti.jee.run_or_die.exposition.dto.participant.zombie.responses;

import fr.epita.mti.jee.run_or_die.exposition.dto.participant.zombie.ZombieDto;

import java.util.List;

public record ZombiesResponse(
    List<ZombieDto> zombies
) {}
