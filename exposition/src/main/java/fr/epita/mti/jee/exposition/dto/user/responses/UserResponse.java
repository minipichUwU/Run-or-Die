package fr.epita.mti.jee.exposition.dto.user.responses;

import fr.epita.mti.jee.domain.models.utilisateur.Utilisateur;

public record UserResponse(
    String username,
    boolean isCertified
) {
    public UserResponse(Utilisateur domainUser) {
        this(domainUser.username(), domainUser.isIllegallyLicensed());
    }
}
