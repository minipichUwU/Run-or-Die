package fr.epita.mti.jee.domain.model.common;

import fr.epita.mti.jee.domain.exception.plage_horaire.InvalidHourException;

import java.util.Objects;

public record Heure(
    int heure
) {
    public Heure {
        if (heure < 0 || heure > 24) {
            throw new InvalidHourException();
        }
    }

    public boolean isBefore(Heure when) {
        return this.heure < when.heure();
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
