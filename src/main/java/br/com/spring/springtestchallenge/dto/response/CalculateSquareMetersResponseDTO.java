package br.com.spring.springtestchallenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CalculateSquareMetersResponseDTO {

    private final String propName;

    private final Double propSquareMeters;
}
