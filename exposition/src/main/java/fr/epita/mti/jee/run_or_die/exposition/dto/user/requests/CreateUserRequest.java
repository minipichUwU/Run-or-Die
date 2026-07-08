package fr.epita.mti.jee.run_or_die.exposition.dto.user.requests;

import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserRequest(
    @Schema(example = "Xx_Super_Mail_Trop_Coool_xX@epita.fr")
    String email,

    @Schema(example = "123456789")
    // sécurité max.
    String password
) {
    public Utilisateur createDomainUtilisateur() {
        return Utilisateur.createNew(email, password);
    }
}
