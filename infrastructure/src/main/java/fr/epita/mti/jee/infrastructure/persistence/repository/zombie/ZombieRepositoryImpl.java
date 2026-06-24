package fr.epita.mti.jee.infrastructure.persistence.repository.zombie;

import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.domain.model.zombie.Zombie;
import fr.epita.mti.jee.domain.repository.ZombieRepository;
import fr.epita.mti.jee.infrastructure.persistence.entity.EmbeddableTimeSlot;
import fr.epita.mti.jee.infrastructure.persistence.entity.ZombieJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.repository.edition.EditionRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ZombieRepositoryImpl implements ZombieRepository {

    private final ZombieRepositoryJPA  zombieRepositoryJPA;
    private final EditionRepositoryJPA editionRepositoryJPA;

    @Autowired
    public ZombieRepositoryImpl(
        ZombieRepositoryJPA zombieRepositoryJPA,
        EditionRepositoryJPA editionRepositoryJPA
    ) {
        this.zombieRepositoryJPA  = zombieRepositoryJPA;
        this.editionRepositoryJPA = editionRepositoryJPA;
    }

    @Override
    public Zombie create(Zombie zombie) {
        return zombieRepositoryJPA.save(toEntity(zombie)).toDomain();
    }

    @Override
    public List<Zombie> getByEmail(String email) {
        return zombieRepositoryJPA
            .findByEmail(email)
            .stream()
            .map(ZombieJpaEntity::toDomain)
            .toList();
    }

    @Override
    public Set<Zombie> getByEmailInEdition(String email, Edition edition) {
        return zombieRepositoryJPA
            .findByEmailAndEdition(email, editionRepositoryJPA.getReferenceById(edition.nom()))
            .stream()
            .map(ZombieJpaEntity::toDomain)
            .collect(Collectors.toSet());
    }

    @Override
    public void delete(Zombie zombie) {
        zombieRepositoryJPA.delete(toEntity(zombie));
    }

    @Override
    public void updateTimeSlot(Zombie zombie) {
        zombieRepositoryJPA.updateTimeSlotById(
            new EmbeddableTimeSlot(zombie.plageHoraire()),
            zombie.id()
        );
    }

    private ZombieJpaEntity toEntity(Zombie zombie) {
        return new ZombieJpaEntity(
            zombie.id(),
            zombie.email(),
            editionRepositoryJPA.getReferenceById(zombie.edition().nom()),
            new EmbeddableTimeSlot(zombie.plageHoraire())
        );
    }
}
