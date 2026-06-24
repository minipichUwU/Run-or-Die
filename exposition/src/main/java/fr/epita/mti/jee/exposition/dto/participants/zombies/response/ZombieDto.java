package fr.epita.mti.jee.exposition.dto.participants.zombies.response;

import fr.epita.mti.jee.domain.model.zombie.Zombie;
import fr.epita.mti.jee.exposition.dto.TimeSlotResponse;
import fr.epita.mti.jee.exposition.dto.participants.ParticipantEditionDto;

public record ZombieDto(
    Long id,
    String email,
    ParticipantEditionDto edition,
    TimeSlotResponse timeSlot
) {
    public ZombieDto(Zombie zombie) {
        this(
            zombie.id(),
            zombie.email(),
            new ParticipantEditionDto(zombie.edition()),
            new TimeSlotResponse(zombie.plageHoraire())
        );
    }
}
