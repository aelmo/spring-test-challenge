package br.com.spring.springtestchallenge.service;

import br.com.spring.springtestchallenge.dto.request.PropertyRequestDTO;
import br.com.spring.springtestchallenge.dto.request.RoomRequestDTO;
import br.com.spring.springtestchallenge.exceptions.DistrictNotFoundException;
import br.com.spring.springtestchallenge.service.impl.DistrictServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DistrictServiceTest {

    private DistrictService districtService;

    @Mock
    private PropertyRequestDTO propertyRequestDTO;

    @Before
    public void setup() {
        districtService = new DistrictServiceImpl();
    }

    @Before
    public void init() {
        propertyRequestDTO = new PropertyRequestDTO(
                "Casa",
                "Teste",
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
    public void shouldReturnPricePerDistrictIfDistrictExists() throws DistrictNotFoundException {
        propertyRequestDTO.setPropDistrict("Cachoeira");
        assertThat(districtService.priceBerDistrict(propertyRequestDTO.getPropDistrict())).isEqualTo(200D);
    }

    @Test
    public void shouldReturnExceptionIfDistrictNotExists() {
        Exception exception = assertThrows(DistrictNotFoundException.class, () -> districtService.priceBerDistrict(propertyRequestDTO.getPropDistrict()));

        String expectedMessage = "District not found: Teste.";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

}
