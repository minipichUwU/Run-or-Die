package fr.epita.mti.jee.run_or_die.exposition.dto.participant.zombie;

public record HourlyZombieCountDto(
    int hour,
    long quantity
) {}
