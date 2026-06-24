package fr.epita.mti.jee.exposition.dto.participants.zombies.request;

import fr.epita.mti.jee.domain.model.common.Heure;
import fr.epita.mti.jee.domain.model.common.PlageHoraire;
import fr.epita.mti.jee.domain.model.edition.Edition;
import fr.epita.mti.jee.domain.model.zombie.Zombie;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public record CreateZombieRequest(
    @Schema(example = "Run or Die — Édition 31 Octobre 2027")
    String editionName,

    @Schema(example = "13")
    int startingHour,

    @Schema(example = "14")
    int endingHour
) {
    public Zombie createDomainZombie() {
        String username = Objects
            .requireNonNull(SecurityContextHolder.getContext().getAuthentication())
            .getName();

        return new Zombie(
            username,
            new Edition(editionName),
            new PlageHoraire(
                new Heure(startingHour),
                new Heure(endingHour)
            )
        );
    }
}
