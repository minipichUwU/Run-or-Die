package fr.epita.mti.jee.domain.model.zombie;

import fr.epita.mti.jee.domain.exception.CancelledEditionException;
import fr.epita.mti.jee.domain.exception.participants.InvalidRegistrationDateException;
import fr.epita.mti.jee.domain.exception.participants.zombie.MaxZombieCapacityReachedException;
import fr.epita.mti.jee.domain.exception.participants.zombie.OutsideEditionTimeSlotException;
import fr.epita.mti.jee.domain.exception.participants.zombie.OverlappingZombieAffectationsException;
import fr.epita.mti.jee.domain.model.common.PlageHoraire;
import fr.epita.mti.jee.domain.model.edition.Edition;

import java.time.LocalDate;
import java.util.List;

public record Zombie(
    Long id,
    String email,
    Edition edition,
    PlageHoraire plageHoraire
) {
    public Zombie(String email, Edition edition, PlageHoraire plageHoraire) {
        this(null, email, edition, plageHoraire);
    }

    public Zombie(String username) {
        this(null, username, null, null);
    }

    public static Zombie createNew(String email, Edition edition, PlageHoraire timeSlot) {
        applyConstraints(null, email, edition, timeSlot);
        return new Zombie(email, edition, timeSlot);
    }

    public static Zombie updateTimeSlot(
        List<Long> surroundingIds,
        String email,
        Edition edition,
        PlageHoraire timeSlot
    ) {
        applyConstraints(surroundingIds, email, edition, timeSlot);
        return new Zombie(surroundingIds.getFirst(), email, edition, timeSlot);
    }

    private static void applyConstraints(
        List<Long> surroundingIds,
        String email,
        Edition edition,
        PlageHoraire timeSlot
    ) {
        if (edition.estAnnulee()) {
            throw new CancelledEditionException("Participation refusée");
        }

        if (edition.date().isBefore(LocalDate.now().plusDays(1))) {
            throw new InvalidRegistrationDateException();
        }

        if (!edition.plageHoraire().contains(timeSlot)) {
            throw new OutsideEditionTimeSlotException();
        }

        if (edition
            .participants()
            .zombies()
            .stream()
            .filter(z -> surroundingIds == null || !surroundingIds.contains(z.id()))
            .anyMatch(z -> z.email().equals(email) && z.plageHoraire().overlap(timeSlot))
        ) {
            throw new OverlappingZombieAffectationsException();
        }

        for (int h = timeSlot.debut().heure(); h < timeSlot.fin().heure(); h++) {
            int hour = h;
            if (edition
                    .participants()
                    .zombies()
                    .stream()
                    .filter(z -> surroundingIds == null ||
                                 !surroundingIds.contains(z.id()) &&
                                 z.plageHoraire().contains(hour))
                    .count() + 1 > edition.participants().capaciteMax().zombies()
            ) {
                throw new MaxZombieCapacityReachedException(timeSlot);
            }
        }
    }
}
