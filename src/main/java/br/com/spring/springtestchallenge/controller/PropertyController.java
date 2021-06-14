package br.com.spring.springtestchallenge.controller;

import br.com.spring.springtestchallenge.dto.request.PropertyRequestDTO;
import br.com.spring.springtestchallenge.dto.response.CalculatePropertyValueResponseDTO;
import br.com.spring.springtestchallenge.dto.response.CalculateSquareMetersResponseDTO;
import br.com.spring.springtestchallenge.dto.response.DefineBiggestRoomResponseDTO;
import br.com.spring.springtestchallenge.dto.response.DefineSquareMetersPerRoomResponseDTO;
import br.com.spring.springtestchallenge.exceptions.DistrictNotFoundException;
import br.com.spring.springtestchallenge.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/propriedade")
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/calculaMetrosQuadrados")
    public ResponseEntity<CalculateSquareMetersResponseDTO> calculateSquareMeters(@Valid @RequestBody final PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(new CalculateSquareMetersResponseDTO(propertyRequestDTO.getPropName(), propertyService.calculatesSquareMeters(propertyRequestDTO)), HttpStatus.OK);
    }

    @PostMapping("/calculaValorPropriedade")
    public ResponseEntity<CalculatePropertyValueResponseDTO> calculatePropertyValue(@Valid @RequestBody final PropertyRequestDTO propertyRequestDTO) throws DistrictNotFoundException {
        return new ResponseEntity<>(new CalculatePropertyValueResponseDTO(propertyRequestDTO.getPropName(), propertyService.calculatesPropertyValue(propertyRequestDTO)), HttpStatus.OK);
    }

    @PostMapping("/determinaMaiorSala")
    public ResponseEntity<DefineBiggestRoomResponseDTO> defineBiggestRoom(@Valid @RequestBody final PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(new DefineBiggestRoomResponseDTO(propertyRequestDTO.getPropName(), propertyService.definesBiggestRoom(propertyRequestDTO)), HttpStatus.OK);
    }

    @PostMapping("/determinaMetrosQuadradosComodo")
    public ResponseEntity<DefineSquareMetersPerRoomResponseDTO> defineSquareMetersPerRoom(@Valid @RequestBody final PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(new DefineSquareMetersPerRoomResponseDTO(propertyRequestDTO.getPropName(), propertyService.definesSquareMetersPerRoom(propertyRequestDTO)), HttpStatus.OK);
    }
}
