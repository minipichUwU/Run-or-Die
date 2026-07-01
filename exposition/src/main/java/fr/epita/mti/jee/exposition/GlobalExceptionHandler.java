package fr.epita.mti.jee.exposition;

import fr.epita.mti.jee.domain.exceptions.common.InvalidObjectException;
import fr.epita.mti.jee.domain.exceptions.common.NotFoundException;
import fr.epita.mti.jee.exposition.dto.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidObjectException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEditionException(InvalidObjectException ex) {
        ErrorResponse error = new ErrorResponse(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEditionException(NotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
