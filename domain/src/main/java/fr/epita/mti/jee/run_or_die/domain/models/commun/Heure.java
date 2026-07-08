package fr.epita.mti.jee.run_or_die.domain.models.commun;

import fr.epita.mti.jee.run_or_die.domain.exceptions.plage_horaire.InvalidHourException;

import java.util.Objects;

public final class Heure {
    private final int heure;

    public Heure(int hour) {
        if (hour < 0 || hour > 24) {
            throw new InvalidHourException();
        }
        this.heure = hour;
    }

    public boolean isBefore(Heure when) {
        return this.heure < when.hour();
    }

    public int hour() {
        return heure;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {return false;}
        Heure heure1 = (Heure) o;
        return heure == heure1.heure;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(heure);
    }

    @Override
    public String toString() {
        return heure + ":00";
    }
}
