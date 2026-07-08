package fr.epita.mti.jee.run_or_die.exposition.dto.participant.runner.Responses;

import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import fr.epita.mti.jee.run_or_die.exposition.dto.participant.runner.RunnerEditionAvailabilityDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record RunnerEditionsAvailabilityResponse(
    List<RunnerEditionAvailabilityDto> editions
) {
    public static RunnerEditionsAvailabilityResponse fromMap(Map<Edition, Long> availabilityMap) {
        List<RunnerEditionAvailabilityDto> editions = new ArrayList<>();

        availabilityMap.forEach((edition, remainingCapacity) -> {
            editions.add(new RunnerEditionAvailabilityDto(edition, remainingCapacity));
        });

        return new RunnerEditionsAvailabilityResponse(editions);
    }
}
