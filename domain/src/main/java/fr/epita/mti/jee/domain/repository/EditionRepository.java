package fr.epita.mti.jee.domain.repository;

import fr.epita.mti.jee.domain.models.coureur.Coureur;
import fr.epita.mti.jee.domain.models.edition.Edition;
import fr.epita.mti.jee.domain.models.zombie.Zombie;

import java.util.Optional;
import java.util.Set;

public interface EditionRepository {

    void organize(Edition domainEdition);

    Set<Edition> getAllEditions();

    Optional<Edition> get(Edition domainEditionName);

    Set<Edition> getActiveEditions();

    boolean existsEditionWithName(String name);

    boolean doOverlapAnotherEdition(Edition domainEdition);

    void delete(Edition domainEditionName);

    void cancel(Edition domainEditionName);

    void affect(Zombie zombie);

    void register(Coureur runner);

    Set<Edition> getEditionWithRegisteredRunner(Coureur runnerName);
}
