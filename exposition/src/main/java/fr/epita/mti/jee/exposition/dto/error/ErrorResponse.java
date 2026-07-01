package fr.epita.mti.jee.exposition.dto.error;

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
