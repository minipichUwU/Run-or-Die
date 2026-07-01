package fr.epita.mti.jee.domain.models.commun;

import fr.epita.mti.jee.domain.exceptions.plage_horaire.InvalidTimeSlotException;

import java.util.Objects;

public final class PlageHoraire {
    private final Heure debut;
    private final Heure fin;

    public PlageHoraire(Heure start, Heure end) {
        if (!start.isBefore(end)) {
            throw new InvalidTimeSlotException();
        }
        this.debut = start;
        this.fin   = end;
    }

    public boolean contains(PlageHoraire timeSlot) {
        return debut.hour() <= timeSlot.start().hour() &&
               timeSlot.end().hour() <= fin.hour();
    }

    public boolean overlap(PlageHoraire with) {
        return debut.isBefore(with.end()) && with.start().isBefore(fin);
    }

    public Heure start() {
        return debut;
    }

    public Heure end() {
        return fin;
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
