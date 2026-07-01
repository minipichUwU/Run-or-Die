package fr.epita.mti.jee.exposition.dto.edition;

import fr.epita.mti.jee.domain.models.zombie.Zombie;
import fr.epita.mti.jee.exposition.dto.TimeSlotDto;

public record EditionZombieDto(
    Long id,
    String email,
    TimeSlotDto timeSlot
) {
    public EditionZombieDto(Zombie zombie) {
        this(
            zombie.id(),
            zombie.user().username(),
            new TimeSlotDto(zombie.timeSlot())
        );
    }
}