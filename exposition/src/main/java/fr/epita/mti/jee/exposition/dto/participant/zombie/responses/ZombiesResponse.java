package fr.epita.mti.jee.exposition.dto.participant.zombie.responses;

import fr.epita.mti.jee.exposition.dto.participant.zombie.ZombieDto;

import java.util.List;

public record ZombiesResponse(
    List<ZombieDto> zombies
) {}
