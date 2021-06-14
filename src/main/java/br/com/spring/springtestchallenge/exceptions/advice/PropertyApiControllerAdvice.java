package br.com.spring.springtestchallenge.exceptions.advice;

import br.com.spring.springtestchallenge.dto.error.ErrorResponseDTO;
import br.com.spring.springtestchallenge.exceptions.DistrictNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class PropertyApiControllerAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleDefault(MethodArgumentNotValidException ex) {
        var responseBody = new ErrorResponseDTO(
                Objects.requireNonNull(ex.getFieldError().getDefaultMessage()),
                ex.getFieldError().getField(),
                HttpStatus.BAD_REQUEST.toString()
        );

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DistrictNotFoundException.class})
    public ResponseEntity<Object> handleDistrictNotFoundException(DistrictNotFoundException ex) {
        var responseBody = new ErrorResponseDTO(
                Objects.requireNonNull(ex.getMessage()),
                ex.getClass().getName(),
                HttpStatus.NOT_FOUND.toString()
        );

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
