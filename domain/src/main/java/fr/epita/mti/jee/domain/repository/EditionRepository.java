package fr.epita.mti.jee.domain.repository;

import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.domain.model.zombie.Zombie;

import java.util.List;
import java.util.Optional;

public interface EditionRepository {

    void create(Edition edition);

    List<Edition> getAll();

    Optional<Edition> getEdition(Edition edition);

    List<Edition> getActive();

    boolean existsByName(String name);

    boolean canReserveTimeSlot(Edition edition);

    void delete(Edition edition);

    void cancel(Edition edition);

    void register(Zombie zombie);
}
