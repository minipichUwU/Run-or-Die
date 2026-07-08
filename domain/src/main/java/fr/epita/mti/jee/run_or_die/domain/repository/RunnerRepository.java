package fr.epita.mti.jee.run_or_die.domain.repository;

import fr.epita.mti.jee.run_or_die.domain.models.coureur.Coureur;

public interface RunnerRepository {
    Coureur create(Coureur runner);
}
