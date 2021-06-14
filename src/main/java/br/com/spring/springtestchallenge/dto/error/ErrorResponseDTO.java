package br.com.spring.springtestchallenge.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseDTO {

    private final String message;

    private final String cause;

    private final String status;
}
