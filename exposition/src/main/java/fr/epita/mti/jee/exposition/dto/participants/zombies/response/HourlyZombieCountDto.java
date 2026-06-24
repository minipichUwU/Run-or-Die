package fr.epita.mti.jee.exposition.dto.participants.zombies.response;

public record HourlyZombieCountDto(
    int hour,
    long quantity
) {}
