package fr.epita.mti.jee.exposition.dto.users;

import fr.epita.mti.jee.domain.model.utilisateur.Utilisateur;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserRequest(
    @Schema(example = "Xx_Super_Mail_Trop_Coool_xX@epita.fr")
    String email,

    @Schema(example = "Mon mot de passe infaillible")
    String password
) {
    public Utilisateur createDomainUtilisateur() {
        return Utilisateur.createNew(email, password);
    }
}
