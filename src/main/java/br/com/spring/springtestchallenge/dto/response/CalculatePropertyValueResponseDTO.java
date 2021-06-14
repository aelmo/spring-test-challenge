package br.com.spring.springtestchallenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CalculatePropertyValueResponseDTO {

    private final String propName;

    private final Double propValue;
}
