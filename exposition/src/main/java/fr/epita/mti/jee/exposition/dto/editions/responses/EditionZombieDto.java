package fr.epita.mti.jee.exposition.dto.editions.responses;

import fr.epita.mti.jee.domain.model.zombie.Zombie;
import fr.epita.mti.jee.exposition.dto.TimeSlotResponse;

public record EditionZombieDto(
    Long id,
    String email,
    TimeSlotResponse timeSlot
) {
    public EditionZombieDto(Zombie zombie) {
        this(
            zombie.id(),
            zombie.email(),
            new TimeSlotResponse(zombie.plageHoraire())
        );
    }
}