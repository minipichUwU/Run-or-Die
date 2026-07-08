package fr.epita.mti.jee.run_or_die.exposition.dto.edition;

import fr.epita.mti.jee.run_or_die.domain.models.zombie.Zombie;
import fr.epita.mti.jee.run_or_die.exposition.dto.TimeSlotDto;

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