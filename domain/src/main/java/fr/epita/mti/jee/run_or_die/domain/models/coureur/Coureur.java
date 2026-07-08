package fr.epita.mti.jee.run_or_die.domain.models.coureur;

import fr.epita.mti.jee.run_or_die.domain.exceptions.edition.CancelledEditionException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.InvalidRegistrationDateException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.runner.AlreadyRegisteredException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.runner.MaxRunnerCapacityReachedException;
import fr.epita.mti.jee.run_or_die.domain.exceptions.participants.runner.RegisteredAsZombieException;
import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;

import java.time.LocalDate;
import java.util.Objects;

public final class Coureur {
    private final Long        id;
    private final Utilisateur utilisateur;
    private final Edition     edition;

    public Coureur(
        Long id,
        Utilisateur utilisateur,
        Edition edition
    ) {
        this.id          = id;
        this.utilisateur = utilisateur;
        this.edition     = edition;
    }

    public Coureur(Utilisateur user, Edition edition) {
        this(null, user, edition);
    }

    public Coureur(Utilisateur userName) {
        this(null, userName, null);
    }

    public static Coureur createNew(Utilisateur user, Edition edition) {
        if (edition.isCancelled()) {
            throw new CancelledEditionException("Participation refusée");
        }

        if (edition.date().isBefore(LocalDate.now().plusDays(1))) {
            throw new InvalidRegistrationDateException();
        }

        if (edition.hasUserRegisteredAsRunner(user)) {
            throw new AlreadyRegisteredException();
        }

        if (edition.hasUserAffectedAsZombie(user)) {
            throw new RegisteredAsZombieException();
        }

        if (edition.isMaxRunnersCapacityReached()) {
            throw new MaxRunnerCapacityReachedException();
        }

        return new Coureur(user, edition);
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (obj == null || obj.getClass() != this.getClass()) {return false;}
        var that = (Coureur) obj;
        return Objects.equals(this.id, that.id) &&
               Objects.equals(this.utilisateur, that.utilisateur) &&
               Objects.equals(this.edition, that.edition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utilisateur, edition);
    }

    @Override
    public String toString() {
        return "Coureur[" +
               "id=" + id + ", " +
               "utilisateur=" + utilisateur + ", " +
               "edition=" + edition + ']';
    }
}
