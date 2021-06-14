package br.com.spring.springtestchallenge.service;

import br.com.spring.springtestchallenge.dto.request.PropertyRequestDTO;
import br.com.spring.springtestchallenge.dto.SquareMetersPerRoomDTO;
import br.com.spring.springtestchallenge.exceptions.DistrictNotFoundException;

import java.util.List;

public interface PropertyService {

    Double calculatesSquareMeters(final PropertyRequestDTO propertyRequestDTO);

    Double calculatesPropertyValue(final PropertyRequestDTO propertyRequestDTO) throws DistrictNotFoundException;

    String definesBiggestRoom(final PropertyRequestDTO propertyRequestDTO);

    List<SquareMetersPerRoomDTO> definesSquareMetersPerRoom(final PropertyRequestDTO propertyRequestDTO);
}
