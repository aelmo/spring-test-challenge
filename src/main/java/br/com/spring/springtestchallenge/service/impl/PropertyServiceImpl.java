package br.com.spring.springtestchallenge.service.impl;

import br.com.spring.springtestchallenge.dto.request.PropertyRequestDTO;
import br.com.spring.springtestchallenge.dto.request.RoomRequestDTO;
import br.com.spring.springtestchallenge.dto.SquareMetersPerRoomDTO;
import br.com.spring.springtestchallenge.exceptions.DistrictNotFoundException;
import br.com.spring.springtestchallenge.service.DistrictService;
import br.com.spring.springtestchallenge.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PropertyServiceImpl implements PropertyService {

    private final DistrictService districtService;

    @Override
    public Double calculatesSquareMeters(final PropertyRequestDTO propertyRequestDTO) {
        double metrosQuadrados = 0D;
        for (RoomRequestDTO room : propertyRequestDTO.getRooms())
            metrosQuadrados += (room.getRoomLength() * room.getRoomWidth());
        return metrosQuadrados;
    }

    @Override
    public Double calculatesPropertyValue(final PropertyRequestDTO propertyRequestDTO) throws DistrictNotFoundException {
        double valorPropriedade = 0D;
        double valorBairro = districtService.priceBerDistrict(propertyRequestDTO.getPropDistrict());
        for (RoomRequestDTO room : propertyRequestDTO.getRooms()) {
            double squareMeters = room.getRoomWidth() * room.getRoomLength();
            valorPropriedade += (squareMeters * valorBairro);
        }
        return valorPropriedade;
    }

    @Override
    public String definesBiggestRoom(final PropertyRequestDTO propertyRequestDTO) {
        RoomRequestDTO maiorSala = propertyRequestDTO.getRooms().get(0);
        double metrosQuadradosMaiorSala = 0D;
        for (RoomRequestDTO room : propertyRequestDTO.getRooms())
            if (room.getRoomLength() * room.getRoomWidth() > metrosQuadradosMaiorSala) {
                metrosQuadradosMaiorSala = room.getRoomWidth() * room.getRoomLength();
                maiorSala = room;
            }
        return maiorSala.getRoomName();
    }

    @Override
    public List<SquareMetersPerRoomDTO> definesSquareMetersPerRoom(final PropertyRequestDTO propertyRequestDTO) {
        List<SquareMetersPerRoomDTO> squareMetersPerRoom = new ArrayList<>();
        for (RoomRequestDTO room : propertyRequestDTO.getRooms()) {
            double squareMeters = room.getRoomWidth() * room.getRoomLength();
            SquareMetersPerRoomDTO squareMetersPerRoomDTO = new SquareMetersPerRoomDTO(
                    room.getRoomName(),
                    squareMeters
            );
            squareMetersPerRoom.add(squareMetersPerRoomDTO);
        }
        return squareMetersPerRoom;
    }
}
