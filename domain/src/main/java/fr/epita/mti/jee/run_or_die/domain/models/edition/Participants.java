package fr.epita.mti.jee.run_or_die.domain.models.edition;

import fr.epita.mti.jee.run_or_die.domain.models.commun.PlageHoraire;
import fr.epita.mti.jee.run_or_die.domain.models.coureur.Coureur;
import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.run_or_die.domain.models.zombie.Zombie;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Participants {
    private final CapaciteMax  capaciteMax;
    private final Set<Zombie>  zombies;
    private final Set<Coureur> coureurs;

    public Participants(
        CapaciteMax maxCapacity,
        Set<Zombie> zombies,
        Set<Coureur> runners
    ) {
        this.capaciteMax = maxCapacity;
        this.zombies     = zombies;
        this.coureurs    = runners;
    }

    public boolean hasThatUserAsRunner(Utilisateur user) {
        return coureurs
            .stream()
            .anyMatch(runner -> Objects.equals(runner.user(), user));
    }

    public boolean hasThatUserAsZombie(Utilisateur user) {
        return zombies
            .stream()
            .anyMatch(zombie -> Objects.equals(zombie.user(), user));
    }

    public boolean hasThatUserAsZombieBetweenTimeSlot(
        Utilisateur user,
        PlageHoraire timeSlot
    ) {
        return zombies
            .stream()
            .anyMatch(zombie -> Objects.equals(zombie.user(), user) &&
                                zombie.timeSlot().overlap(timeSlot));
    }

    public boolean hasThatUserAsZombieBetweenTimeSlot(
        Utilisateur user,
        PlageHoraire timeSlot,
        List<Long> idsToIgnore
    ) {
        return zombies
            .stream()
            .filter(zombie -> idsToIgnore == null || !idsToIgnore.contains(zombie.id()))
            .anyMatch(zombie -> Objects.equals(zombie.user(), user) &&
                                zombie.timeSlot().overlap(timeSlot));
    }

    public boolean isMaxRunnersCapacityReached() {
        return coureurs.size() >= capaciteMax.runners();
    }

    public boolean isMaxZombiesCapacityReachedBetween(
        PlageHoraire timeSlot
    ) {
        return computeZombieHistogramBetween(timeSlot)
            .values()
            .stream()
            .anyMatch(zombieQuantity -> zombieQuantity >= capaciteMax.zombies());
    }

    public boolean isMaxZombiesCapacityReachedBetween(
        PlageHoraire timeSlot,
        List<Long> idsToIgnore
    ) {
        return computeZombieHistogramBetween(timeSlot, idsToIgnore)
            .values()
            .stream()
            .anyMatch(zombieQuantity -> zombieQuantity >= capaciteMax.zombies());
    }

    public Map<Integer, Long> computeZombieHistogramBetween(PlageHoraire timeSlot) {
        Map<Integer, Long> histogram = initHistogram(timeSlot);

        zombies.stream()
            .flatMapToInt(z -> IntStream.range(
                z.timeSlot().start().hour(),
                z.timeSlot().end().hour()
            ))
            .forEach(hour -> histogram.computeIfPresent(hour, (h, count) -> count + 1));

        return histogram;
    }

    private Map<Integer, Long> computeZombieHistogramBetween(
        PlageHoraire timeSlot,
        List<Long> idsToIgnore
    ) {
        Map<Integer, Long> histogram = initHistogram(timeSlot);

        zombies.stream()
            .filter(zombie -> idsToIgnore == null || !idsToIgnore.contains(zombie.id()))
            .flatMapToInt(z -> IntStream.range(
                z.timeSlot().start().hour(),
                z.timeSlot().end().hour()
            ))
            .forEach(hour -> histogram.computeIfPresent(hour, (h, count) -> count + 1));

        return histogram;
    }

    private Map<Integer, Long> initHistogram(PlageHoraire timeSlot) {
        return IntStream
            .range(timeSlot.start().hour(), timeSlot.end().hour())
            .boxed()
            .collect(Collectors.toMap(hour -> hour, hour -> 0L));
    }

    public CapaciteMax maxCapacity() {
        return capaciteMax;
    }

    public Set<Zombie> zombies() {
        return zombies;
    }

    public Set<Coureur> runners() {
        return coureurs;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null || obj.getClass() != this.getClass()) {return false;}
        var that = (Participants) obj;
        return Objects.equals(this.capaciteMax, that.capaciteMax) &&
               Objects.equals(this.zombies, that.zombies) &&
               Objects.equals(this.coureurs, that.coureurs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capaciteMax, zombies, coureurs);
    }

    @Override
    public String toString() {
        return "Participants[" +
               "capaciteMax=" + capaciteMax + ", " +
               "zombies=" + zombies + ", " +
               "coureurs=" + coureurs + "]";
    }
}
