package fr.epita.mti.jee.exposition.dto.participants.zombies.response;

import java.util.List;

public record ZombiesResponse(
    List<ZombieDto> zombies
) {}
