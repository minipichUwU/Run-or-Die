package fr.epita.mti.jee.run_or_die.domain.models.edition;

import fr.epita.mti.jee.run_or_die.domain.exceptions.edition.InvalidEditionDateException;
import fr.epita.mti.jee.run_or_die.domain.models.commun.PlageHoraire;
import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class Edition {
    private final String       nom;
    private final LocalDate    date;
    private final PlageHoraire plageHoraire;
    private final String       lieu;
    private final Participants participants;
    private final boolean      estAnnulee;

    public Edition(
        String name,
        LocalDate date,
        PlageHoraire timeSlot,
        String location,
        Participants participants,
        boolean cancelled
    ) {
        this.nom          = name;
        this.date         = date;
        this.plageHoraire = timeSlot;
        this.lieu         = location;
        this.participants = participants;
        this.estAnnulee   = cancelled;
    }

    public Edition(
        String name,
        LocalDate date,
        PlageHoraire timeSlot,
        String location,
        CapaciteMax maxCapacity
    ) {
        this(
            name,
            date,
            timeSlot,
            location,
            new Participants(maxCapacity, Set.of(), Set.of()),
            false
        );
    }

    public Edition(String editionName) {
        this(editionName, null, null, null, null, false);
    }

    public static Edition createNew(
        String name,
        LocalDate date,
        PlageHoraire timeSlot,
        String location,
        CapaciteMax maxCapacity
    ) {
        if (date.isBefore(LocalDate.now().plusDays(1))) {
            throw new InvalidEditionDateException();
        }

        return new Edition(name, date, timeSlot, location, maxCapacity);
    }

    public boolean hasUserRegisteredAsRunner(Utilisateur user) {
        return participants.hasThatUserAsRunner(user);
    }

    public boolean hasUserAffectedAsZombie(Utilisateur user) {
        return participants.hasThatUserAsZombie(user);
    }

    public boolean isMaxRunnersCapacityReached() {
        return participants.isMaxRunnersCapacityReached();
    }

    public boolean isMaxZombiesCapacityReachedBetween(PlageHoraire timeSlot) {
        return participants.isMaxZombiesCapacityReachedBetween(timeSlot);
    }

    public boolean isMaxZombiesCapacityReachedBetween(
        PlageHoraire timeSlot,
        List<Long> idsToIgnore
    ) {
        return participants.isMaxZombiesCapacityReachedBetween(timeSlot, idsToIgnore);
    }

    public boolean hasUserAffectedAsZombieBetweenTimeSlot(Utilisateur user, PlageHoraire timeSlot) {
        return participants.hasThatUserAsZombieBetweenTimeSlot(user, timeSlot);
    }

    public boolean hasUserAffectedAsZombieBetweenTimeSlot(
        Utilisateur user,
        PlageHoraire timeSlot,
        List<Long> idsToIgnore
    ) {
        return participants.hasThatUserAsZombieBetweenTimeSlot(user, timeSlot, idsToIgnore);
    }

    public Map<Integer, Long> getZombieHistogram() {
        return participants.computeZombieHistogramBetween(plageHoraire);
    }

    public String name() {
        return nom;
    }

    public LocalDate date() {
        return date;
    }

    public PlageHoraire timeSlot() {
        return plageHoraire;
    }

    public String location() {
        return lieu;
    }

    public Participants participants() {
        return participants;
    }

    public boolean isCancelled() {
        return estAnnulee;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null || obj.getClass() != this.getClass()) {return false;}
        var that = (Edition) obj;
        return Objects.equals(this.nom, that.nom) &&
               Objects.equals(this.date, that.date) &&
               Objects.equals(this.plageHoraire, that.plageHoraire) &&
               Objects.equals(this.lieu, that.lieu) &&
               Objects.equals(this.participants, that.participants) &&
               this.estAnnulee == that.estAnnulee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, date, plageHoraire, lieu, participants, estAnnulee);
    }

    @Override
    public String toString() {
        return "Edition[" +
               "nom=" + nom + ", " +
               "date=" + date + ", " +
               "plageHoraire=" + plageHoraire + ", " +
               "lieu=" + lieu + ", " +
               "participants=" + participants + ", " +
               "estAnnulee=" + estAnnulee + ']';
    }
}
