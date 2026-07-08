package fr.epita.mti.jee.run_or_die.domain.repository;

import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.run_or_die.domain.models.zombie.Zombie;

import java.util.Set;

public interface ZombieRepository {
    Zombie create(Zombie domainZombie);

    Set<Zombie> getWithSameUser(Utilisateur user);

    Set<Zombie> getWithSameUserInEdition(Zombie domainZombie);

    void delete(Zombie domainZombie);

    void updateTimeSlot(Zombie domainZombie);
}
