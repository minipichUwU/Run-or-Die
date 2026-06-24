package fr.epita.mti.jee.domain.model.common;

import fr.epita.mti.jee.domain.exception.plage_horaire.InvalidTimeSlotException;

import java.util.Objects;

public record PlageHoraire(
    Heure debut,
    Heure fin
) {
    public PlageHoraire {
        if (!debut.isBefore(fin)) {
            throw new InvalidTimeSlotException();
        }
    }

    public boolean contains(PlageHoraire timeSlot) {
        return debut.heure() <= timeSlot.debut().heure() &&
               timeSlot.fin().heure() <= fin.heure();
    }

    public boolean contains(int hour) {
        return debut.heure() <= hour && hour < fin.heure();
    }

    public boolean overlap(PlageHoraire with) {
        return debut.isBefore(with.fin()) && with.debut().isBefore(fin);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {return false;}
        PlageHoraire that = (PlageHoraire) o;
        return Objects.equals(fin, that.fin) && Objects.equals(debut, that.debut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debut, fin);
    }

    @Override
    public String toString() {
        return "de " + debut + " à " + fin;
    }
}
