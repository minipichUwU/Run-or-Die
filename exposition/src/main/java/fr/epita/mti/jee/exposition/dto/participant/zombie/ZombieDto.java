package fr.epita.mti.jee.exposition.dto.participant.zombie;

import fr.epita.mti.jee.domain.models.zombie.Zombie;
import fr.epita.mti.jee.exposition.dto.TimeSlotDto;
import fr.epita.mti.jee.exposition.dto.participant.ParticipantEditionDto;

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
