package fr.epita.mti.jee.run_or_die.exposition.dto.participant.zombie;

import fr.epita.mti.jee.run_or_die.domain.models.zombie.Zombie;
import fr.epita.mti.jee.run_or_die.exposition.dto.TimeSlotDto;
import fr.epita.mti.jee.run_or_die.exposition.dto.participant.ParticipantEditionDto;

public record ZombieDto(
    Long id,
    String email,
    ParticipantEditionDto edition,
    TimeSlotDto timeSlot
) {
    public ZombieDto(Zombie zombie) {
        this(
            zombie.id(),
            zombie.user().username(),
            new ParticipantEditionDto(zombie.edition()),
            new TimeSlotDto(zombie.timeSlot())
        );
    }
}
