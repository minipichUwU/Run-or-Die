package fr.epita.mti.jee.exposition.dto.participant.zombie;

public record HourlyZombieCountDto(
    int hour,
    long quantity
) {}
