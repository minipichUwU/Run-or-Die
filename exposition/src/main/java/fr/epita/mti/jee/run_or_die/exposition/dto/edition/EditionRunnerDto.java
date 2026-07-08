package fr.epita.mti.jee.run_or_die.exposition.dto.edition;

import fr.epita.mti.jee.run_or_die.domain.models.coureur.Coureur;

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