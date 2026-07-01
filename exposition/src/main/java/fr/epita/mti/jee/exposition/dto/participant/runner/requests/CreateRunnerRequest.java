package fr.epita.mti.jee.exposition.dto.participant.runner.requests;

import fr.epita.mti.jee.domain.models.coureur.Coureur;
import fr.epita.mti.jee.domain.models.edition.Edition;
import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateRunnerRequest(
    @Schema(example = "Run or Die — Édition Octobre 2027")
    String editionName
) {
    public Coureur createDomainZombie(String username) {
        return new Coureur(new Utilisateur(username), new Edition(editionName));
    }
}
