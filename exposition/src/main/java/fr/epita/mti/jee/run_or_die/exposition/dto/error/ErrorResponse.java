package fr.epita.mti.jee.run_or_die.exposition.dto.error;

import java.time.LocalDateTime;

public record ErrorResponse(
    LocalDateTime timestamp,
    String error,
    String message
) {
    public ErrorResponse(Exception ex) {
        this(
            LocalDateTime.now(),
            ex.getClass().getSimpleName(),
            ex.getMessage()
        );
    }
}
