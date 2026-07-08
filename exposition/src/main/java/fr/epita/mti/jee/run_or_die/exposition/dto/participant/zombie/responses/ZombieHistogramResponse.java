package fr.epita.mti.jee.run_or_die.exposition.dto.participant.zombie.responses;

import fr.epita.mti.jee.run_or_die.exposition.dto.participant.zombie.HourlyZombieCountDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public record ZombieHistogramResponse(
    List<HourlyZombieCountDto> histogram
) {
    public static ZombieHistogramResponse fromMap(Map<Integer, Long> zombiesHistogram) {
        List<HourlyZombieCountDto> histogram = new ArrayList<>();
        zombiesHistogram.forEach((hour, quantity) ->
            histogram.add(
                new HourlyZombieCountDto(hour, quantity))
        );

        histogram.sort(Comparator.comparingInt(HourlyZombieCountDto::hour));

        return new ZombieHistogramResponse(histogram);
    }
}
