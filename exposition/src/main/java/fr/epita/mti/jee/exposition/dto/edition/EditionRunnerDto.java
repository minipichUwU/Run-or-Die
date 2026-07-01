package fr.epita.mti.jee.exposition.dto.edition;

import fr.epita.mti.jee.domain.models.coureur.Coureur;

public record EditionRunnerDto(
    Long id,
    String email
) {
    public EditionRunnerDto(Coureur runner) {
        this(
            runner.id(),
            runner.user().username()
        );
    }
}