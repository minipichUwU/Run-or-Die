package fr.epita.mti.jee.domain.repository;

import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.domain.model.zombie.Zombie;

import java.util.List;
import java.util.Set;

public interface ZombieRepository {
    Zombie create(Zombie zombie);

    List<Zombie> getByEmail(String email);

    Set<Zombie> getByEmailInEdition(String email, Edition edition);

    void delete(Zombie zombie);

    void updateTimeSlot(Zombie zombie);
}
