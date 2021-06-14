package br.com.spring.springtestchallenge.service;

import br.com.spring.springtestchallenge.dto.request.PropertyRequestDTO;
import br.com.spring.springtestchallenge.dto.request.RoomRequestDTO;
import br.com.spring.springtestchallenge.exceptions.DistrictNotFoundException;
import br.com.spring.springtestchallenge.service.impl.PropertyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PropertyServiceTest {

    private PropertyService propertyService;

    @Mock
    private DistrictService districtService;

    @Mock
    private PropertyRequestDTO propertyRequestDTO;

    @Before
    public void setup() {
        propertyService = new PropertyServiceImpl(districtService);
    }

    @Before
    public void init() {
        propertyRequestDTO = new PropertyRequestDTO(
             "Casa",
             "Cachoeira",
                List.of(new RoomRequestDTO(
                        "Banheiro",
                        20D,
                        20D
                ), new RoomRequestDTO(
                        "Sala",
                        10D,
                        10D
                ))
        );
    }

    @Test
    public void shouldReturnCorrectSquareMeters() {
        assertThat(propertyService.calculatesSquareMeters(propertyRequestDTO)).isEqualTo(500D);
    }

    @Test
    public void shouldReturnCorrectPropertyValueIfDistrictExists() throws DistrictNotFoundException {
        when(districtService.priceBerDistrict(propertyRequestDTO.getPropDistrict())).thenReturn(1000D);
        assertThat(propertyService.calculatesPropertyValue(propertyRequestDTO)).isEqualTo(500000);
    }

    @Test
    public void shouldReturnBiggestRoomName() {
        assertThat(propertyService.definesBiggestRoom(propertyRequestDTO)).isEqualTo("Banheiro");
    }

    @Test
    public void shouldReturnCorrectSquareMetersPerRoom() {
        assertThat(propertyService.definesSquareMetersPerRoom(propertyRequestDTO).get(0).getSquareMeters()).isEqualTo(400);
        assertThat(propertyService.definesSquareMetersPerRoom(propertyRequestDTO).get(1).getSquareMeters()).isEqualTo(100);
    }
}
