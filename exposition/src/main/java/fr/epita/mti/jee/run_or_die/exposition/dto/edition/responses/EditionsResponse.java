package fr.epita.mti.jee.run_or_die.exposition.dto.edition.responses;

import java.util.List;

public record EditionsResponse(
    List<EditionResponse> editions
) {}
