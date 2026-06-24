package fr.epita.mti.jee.infrastructure.persistence.repository.edition;

import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.domain.model.zombie.Zombie;
import fr.epita.mti.jee.domain.repository.EditionRepository;
import fr.epita.mti.jee.infrastructure.persistence.entity.EditionJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.entity.EmbeddableMaxCapacity;
import fr.epita.mti.jee.infrastructure.persistence.entity.EmbeddableParticipants;
import fr.epita.mti.jee.infrastructure.persistence.entity.EmbeddableTimeSlot;
import fr.epita.mti.jee.infrastructure.persistence.repository.zombie.ZombieRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EditionRepositoryImpl implements EditionRepository {

    private final EditionRepositoryJPA editionRepositoryJPA;
    private final ZombieRepositoryJPA  zombieRepositoryJPA;

    @Autowired
    public EditionRepositoryImpl(
        EditionRepositoryJPA editionRepositoryJPA,
        ZombieRepositoryJPA zombieRepositoryJPA
    ) {
        this.editionRepositoryJPA = editionRepositoryJPA;
        this.zombieRepositoryJPA  = zombieRepositoryJPA;
    }

    @Override
    public void create(Edition edition) {
        editionRepositoryJPA.save(this.toEntity(edition));
    }

    @Override
    public List<Edition> getAll() {
        return editionRepositoryJPA.findAll().stream().map(EditionJpaEntity::toDomain).toList();
    }

    @Override
    public Optional<Edition> getEdition(Edition edition) {
        return editionRepositoryJPA
            .findByName(edition.nom())
            .stream()
            .map(EditionJpaEntity::toDomain)
            .findFirst();
    }

    @Override
    public List<Edition> getActive() {
        return editionRepositoryJPA
            .findByDateGreaterThanAndCancelledFalse(LocalDate.now())
            .stream()
            .map(EditionJpaEntity::toDomain)
            .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return editionRepositoryJPA.existsByName(name);
    }

    @Override
    public boolean canReserveTimeSlot(Edition edition) {
        return editionRepositoryJPA.hasTimeSlotConflict(
            edition.date(),
            edition.plageHoraire().debut().heure(),
            edition.plageHoraire().fin().heure()
        );
    }

    @Override
    public void delete(Edition edition) {
        editionRepositoryJPA.delete(toEntity(edition));
    }

    @Override
    public void cancel(Edition edition) {
        editionRepositoryJPA.cancelByName(edition.nom());
    }

    @Override
    public void register(Zombie zombie) {
        EditionJpaEntity editionJPA = editionRepositoryJPA.findByName(zombie.edition().nom()).get();
        editionJPA
            .getParticipants()
            .zombies()
            .add(zombieRepositoryJPA.getReferenceById(zombie.id()));
        editionRepositoryJPA.save(editionJPA);
    }

    private EditionJpaEntity toEntity(Edition edition) {
        return new EditionJpaEntity(
            edition.nom(),
            edition.date(),
            new EmbeddableTimeSlot(
                edition.plageHoraire().debut().heure(),
                edition.plageHoraire().fin().heure()
            ),
            edition.lieu(),
            new EmbeddableParticipants(
                new EmbeddableMaxCapacity(
                    edition.participants().capaciteMax().coureurs(),
                    edition.participants().capaciteMax().zombies()
                ),
                edition
                    .participants()
                    .zombies()
                    .stream()
                    .map(zombie -> zombieRepositoryJPA.getReferenceById(zombie.id()))
                    .collect(Collectors.toSet())
            ),
            edition.estAnnulee()
        );
    }
}
