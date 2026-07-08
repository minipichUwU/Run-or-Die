package fr.epita.mti.jee.run_or_die.exposition.dto.user.responses;

import java.util.List;

public record UsersResponse(
    List<UserResponse> users
) {}
