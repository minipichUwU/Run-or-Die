package fr.epita.mti.jee.run_or_die.exposition.dto.user.responses;

import fr.epita.mti.jee.run_or_die.domain.models.utilisateur.Utilisateur;

public record UserResponse(
    String username,
    boolean isCertified
) {
    public UserResponse(Utilisateur domainUser) {
        this(domainUser.username(), domainUser.isLicensed());
    }
}
