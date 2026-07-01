package fr.epita.mti.jee.infrastructure.persistence.repository.zombie;

import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.domain.models.zombie.Zombie;
import fr.epita.mti.jee.domain.repository.ZombieRepository;
import fr.epita.mti.jee.infrastructure.persistence.entities.EmbeddableTimeSlot;
import fr.epita.mti.jee.infrastructure.persistence.entities.ZombieJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.repository.edition.EditionRepositoryJPA;
import fr.epita.mti.jee.infrastructure.persistence.repository.user.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ZombieRepositoryImpl implements ZombieRepository {

    private final ZombieRepositoryJPA  zombieRepositoryJPA;
    private final UserRepositoryJPA    userRepositoryJPA;
    private final EditionRepositoryJPA editionRepositoryJPA;

    @Autowired
    public ZombieRepositoryImpl(
        ZombieRepositoryJPA zombieRepositoryJPA, UserRepositoryJPA userRepositoryJPA,
        EditionRepositoryJPA editionRepositoryJPA
    ) {
        this.zombieRepositoryJPA  = zombieRepositoryJPA;
        this.userRepositoryJPA    = userRepositoryJPA;
        this.editionRepositoryJPA = editionRepositoryJPA;
    }

    @Override
    public Zombie create(Zombie domainZombie) {
        return zombieRepositoryJPA.save(toEntity(domainZombie)).toDomain();
    }

    @Override
    public Set<Zombie> getWithSameUser(Utilisateur user) {

        return zombieRepositoryJPA
            .findByUser(userRepositoryJPA.getReferenceById(user.username()))
            .stream()
            .map(ZombieJpaEntity::toDomain)
            .collect(Collectors.toSet());
    }

    @Override
    public Set<Zombie> getWithSameUserInEdition(Zombie domainZombie) {
        return zombieRepositoryJPA
            .findByUserAndEdition(
                userRepositoryJPA.getReferenceById(domainZombie.user().username()),
                editionRepositoryJPA.getReferenceById(domainZombie.edition().name())
            )
            .stream()
            .map(ZombieJpaEntity::toDomain)
            .collect(Collectors.toSet());
    }

    @Override
    public void delete(Zombie domainZombie) {
        zombieRepositoryJPA.delete(toEntity(domainZombie));
    }

    @Override
    public void updateTimeSlot(Zombie domainZombie) {
        zombieRepositoryJPA.updateTimeSlotById(
            new EmbeddableTimeSlot(domainZombie.timeSlot()),
            domainZombie.id()
        );
    }

    private ZombieJpaEntity toEntity(Zombie zombie) {
        return new ZombieJpaEntity(
            zombie.id(),
            userRepositoryJPA.getReferenceById(zombie.user().username()),
            editionRepositoryJPA.getReferenceById(zombie.edition().name()),
            new EmbeddableTimeSlot(zombie.timeSlot())
        );
    }
}
