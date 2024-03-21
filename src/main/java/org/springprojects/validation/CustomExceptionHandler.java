package org.springprojects.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springprojects.dto.ErrorDTO;
import org.springprojects.exceptions.AlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> generateAlreadyExistsException(AlreadyExistsException ex) {

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());
        errorDTO.setStatus(String.valueOf(ex.getStatus().value()));

        return new ResponseEntity<ErrorDTO>(errorDTO, ex.getStatus());
    }

}