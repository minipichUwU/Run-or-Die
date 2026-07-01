package fr.epita.mti.jee.exposition.dto.user.responses;

import java.util.List;

public record UsersResponse(
    List<UserResponse> users
) {}
