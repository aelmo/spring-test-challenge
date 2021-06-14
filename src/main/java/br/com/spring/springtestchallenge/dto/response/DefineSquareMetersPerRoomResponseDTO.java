package br.com.spring.springtestchallenge.dto.response;

import br.com.spring.springtestchallenge.dto.SquareMetersPerRoomDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DefineSquareMetersPerRoomResponseDTO {

    private final String propName;

    private final List<SquareMetersPerRoomDTO> squareMetersPerRoom;
}
