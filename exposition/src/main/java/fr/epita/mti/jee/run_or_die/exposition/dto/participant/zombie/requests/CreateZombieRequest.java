package fr.epita.mti.jee.run_or_die.exposition.dto.participant.zombie.requests;

import fr.epita.mti.jee.run_or_die.domain.models.commun.Heure;
import fr.epita.mti.jee.run_or_die.domain.models.commun.PlageHoraire;
import fr.epita.mti.jee.run_or_die.domain.models.edition.Edition;
import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;
import fr.epita.mti.jee.run_or_die.domain.models.zombie.Zombie;
import io.swagger.v3.oas.annotations.media.Schema;


public record CreateZombieRequest(
    @Schema(example = "Run or Die — Édition Octobre 2027")
    String editionName,

    @Schema(example = "13")
    int startingHour,

    @Schema(example = "14")
    int endingHour
) {
    public Zombie createDomainZombie(String email) {
        return new Zombie(
            new Utilisateur(email),
            new Edition(editionName),
            new PlageHoraire(
                new Heure(startingHour),
                new Heure(endingHour)
            )
        );
    }
}
