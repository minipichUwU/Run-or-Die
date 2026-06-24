package fr.epita.mti.jee.exposition.dto.editions.responses;

import java.util.List;

public record EditionsResponse(
    List<EditionResponse> editions
) {}
