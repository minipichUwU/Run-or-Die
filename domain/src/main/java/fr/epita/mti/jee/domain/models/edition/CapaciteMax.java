package fr.epita.mti.jee.domain.models.edition;

import java.util.Objects;

public final class CapaciteMax {
    private final long coureurs;
    private final long zombies;

    public CapaciteMax(
        long runners,
        long zombies
    ) {
        this.coureurs = runners;
        this.zombies  = zombies;
    }

    public long runners() {
        return coureurs;
    }

    public long zombies() {
        return zombies;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null || obj.getClass() != this.getClass()) {return false;}
        var that = (CapaciteMax) obj;
        return this.coureurs == that.coureurs &&
               this.zombies == that.zombies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coureurs, zombies);
    }

    @Override
    public String toString() {
        return "CapaciteMax[" +
               "coureurs=" + coureurs + ", " +
               "zombies=" + zombies + ']';
    }
}