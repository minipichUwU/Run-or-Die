package fr.epita.mti.jee.run_or_die.domain.models.zombie;

import fr.epita.mti.jee.run_or_die.domain.exceptions.edition.CancelledEditionException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.InvalidRegistrationDateException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.zombie.MaxZombieCapacityReachedException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.zombie.OutsideEditionTimeSlotException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.zombie.OverlappingZombieAffectationsException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.zombie.RegisteredAsRunnerException;
import fr.epita.mti.jee.run_or_die.domain.models.commun.PlageHoraire;
import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public final class Zombie {
    private final Long         id;
    private final Utilisateur  utilisateur;
    private final Edition      edition;
    private final PlageHoraire plageHoraire;

    public Zombie(Long id, Utilisateur user, Edition edition, PlageHoraire timeSlot) {
        this.id           = id;
        this.utilisateur  = user;
        this.edition      = edition;
        this.plageHoraire = timeSlot;
    }

    public Zombie(Utilisateur user, Edition edition, PlageHoraire plageHoraire) {
        this(null, user, edition, plageHoraire);
    }

    public Zombie(Utilisateur userName) {
        this(null, userName, null, null);
    }

    public static Zombie createNew(Utilisateur userName, Edition edition, PlageHoraire timeSlot) {
        applyConstraints(userName, edition, timeSlot);
        return new Zombie(userName, edition, timeSlot);
    }

    public static Zombie setNewTimeSlot(
        List<Long> surroundingIds,
        Utilisateur userName,
        Edition edition,
        PlageHoraire timeSlot
    ) {
        applyConstraints(userName, edition, timeSlot, surroundingIds);
        return new Zombie(surroundingIds.getFirst(), userName, edition, timeSlot);
    }

    private static void applyConstraints(
        Utilisateur user,
        Edition edition,
        PlageHoraire timeSlot
    ) {
        applySimpleConstraints(user, edition, timeSlot);

        if (edition.hasUserAffectedAsZombieBetweenTimeSlot(user, timeSlot)) {
            throw new OverlappingZombieAffectationsException();
        }

        if (edition.isMaxZombiesCapacityReachedBetween(timeSlot)) {
            throw new MaxZombieCapacityReachedException();
        }
    }

    private static void applyConstraints(
        Utilisateur user,
        Edition edition,
        PlageHoraire timeSlot,
        List<Long> surroundingIds
    ) {
        applySimpleConstraints(user, edition, timeSlot);

        if (edition.hasUserAffectedAsZombieBetweenTimeSlot(user, timeSlot, surroundingIds)) {
            throw new OverlappingZombieAffectationsException();
        }

        if (edition.isMaxZombiesCapacityReachedBetween(timeSlot, surroundingIds)) {
            throw new MaxZombieCapacityReachedException();
        }
    }

    private static void applySimpleConstraints(
        Utilisateur user,
        Edition edition,
        PlageHoraire timeSlot
    ) {
        if (edition.isCancelled()) {
            throw new CancelledEditionException("Participation refusée");
        }

        if (edition.date().isBefore(LocalDate.now().plusDays(1))) {
            throw new InvalidRegistrationDateException();
        }

        if (!edition.timeSlot().contains(timeSlot)) {
            throw new OutsideEditionTimeSlotException();
        }

        if (edition.hasUserRegisteredAsRunner(user)) {
            throw new RegisteredAsRunnerException();
        }
    }

    public Long id() {
        return id;
    }

    public Utilisateur user() {
        return utilisateur;
    }

    public Edition edition() {
        return edition;
    }

    public PlageHoraire timeSlot() {
        return plageHoraire;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null || obj.getClass() != this.getClass()) {return false;}
        var that = (Zombie) obj;
        return Objects.equals(this.id, that.id) &&
               Objects.equals(this.utilisateur, that.utilisateur) &&
               Objects.equals(this.edition, that.edition) &&
               Objects.equals(this.plageHoraire, that.plageHoraire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utilisateur, edition, plageHoraire);
    }

    @Override
    public String toString() {
        return "Zombie[" +
               "id=" + id + ", " +
               "utilisateur=" + utilisateur + ", " +
               "edition=" + edition + ", " +
               "plageHoraire=" + plageHoraire + ']';
    }
}
