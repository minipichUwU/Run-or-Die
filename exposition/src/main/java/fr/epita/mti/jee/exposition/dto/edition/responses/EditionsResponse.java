package fr.epita.mti.jee.exposition.dto.edition.responses;

import java.util.List;

public record EditionsResponse(
    List<EditionResponse> editions
) {}
