package br.com.spring.springtestchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SquareMetersPerRoomDTO {

    private final String roomName;

    private final Double squareMeters;
}
