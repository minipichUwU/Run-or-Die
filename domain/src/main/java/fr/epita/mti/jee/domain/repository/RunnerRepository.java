package fr.epita.mti.jee.domain.repository;

import fr.epita.mti.jee.domain.models.coureur.Coureur;

public interface RunnerRepository {
    Coureur create(Coureur runner);
}
