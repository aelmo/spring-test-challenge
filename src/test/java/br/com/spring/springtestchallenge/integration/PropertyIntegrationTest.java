package br.com.spring.springtestchallenge.integration;

import br.com.spring.springtestchallenge.dto.request.PropertyRequestDTO;
import br.com.spring.springtestchallenge.dto.request.RoomRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PropertyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private RoomRequestDTO roomRequestDTOMock;

    @Mock
    private PropertyRequestDTO propertyRequestDTOMock;

    @BeforeEach
    public void init() {
        List<RoomRequestDTO> roomRequestDTOList = new ArrayList<>();

        roomRequestDTOMock = new RoomRequestDTO(
                "Sala",
                10D,
                20D
        );

        roomRequestDTOList.add(roomRequestDTOMock);

        propertyRequestDTOMock = new PropertyRequestDTO(
                "Teste",
                "Cachoeira",
                roomRequestDTOList
        );
    }

    @Test
    public void shouldCalculateSquareMeters() throws Exception {
        MvcResult result = mockMvc.perform(post("/propriedade/calculaMetrosQuadrados", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = "{\"propName\":\"Teste\",\"propSquareMeters\":200.0}";
        String content = result.getResponse().getContentAsString();

        assertEquals(expectedResponse, content);
    }

    @Test
    public void shouldCalculatePropertyValue() throws Exception {
        MvcResult result = mockMvc.perform(post("/propriedade/calculaValorPropriedade", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = "{\"propName\":\"Teste\",\"propValue\":40000.0}";
        String content = result.getResponse().getContentAsString();

        assertEquals(expectedResponse, content);
    }

    @Test
    public void shouldDefineBiggestRoom() throws Exception {
        MvcResult result = mockMvc.perform(post("/propriedade/determinaMaiorSala", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = "{\"propName\":\"Teste\",\"propBiggestRoom\":\"Sala\"}";
        String content = result.getResponse().getContentAsString();

        assertEquals(expectedResponse, content);
    }

    @Test
    public void shouldDefineSquareMetersPerRoom() throws Exception {
        MvcResult result = mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponse = "{\"propName\":\"Teste\",\"squareMetersPerRoom\":[{\"roomName\":\"Sala\",\"squareMeters\":200.0}]}";
        String content = result.getResponse().getContentAsString();

        assertEquals(expectedResponse, content);
    }

    @Test
    public void shouldReturnBadRequestIfPropNameEmpty() throws Exception {
        propertyRequestDTOMock.setPropName("");

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfPropDistrictNameEmpty() throws Exception {
        propertyRequestDTOMock.setPropDistrict("");

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfRoomNameEmpty() throws Exception {
        roomRequestDTOMock.setRoomName("");

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfRoomWidthEmpty() throws Exception {
        roomRequestDTOMock.setRoomWidth(null);

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfRoomLengthEmpty() throws Exception {
        roomRequestDTOMock.setRoomLength(null);

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfPropNameHasMoreThan30Characters() throws Exception {
        propertyRequestDTOMock.setPropName("THIS DEFINITELY HAS MORE THAN 30 CHARACTERS LMAO");

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfPropDistrictNameHasMoreThan45Characters() throws Exception {
        propertyRequestDTOMock.setPropDistrict("THIS DEFINITELY HAS MORE THAN 45 CHARACTERS LMAO THIS DEFINITELY HAS MORE THAN 45 CHARACTERS LMAO");

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfRoomNameHasMoreThan30Characters() throws Exception {
        roomRequestDTOMock.setRoomName("THIS DEFINITELY HAS MORE THAN 30 CHARACTERS LMAO");

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfPropNameStartsWithLowerCase() throws Exception {
        propertyRequestDTOMock.setPropName("propName");

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfRoomNameStartsWithLowerCase() throws Exception {
        roomRequestDTOMock.setRoomName("roomName");

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfRoomWidthGreaterThan25() throws Exception {
        roomRequestDTOMock.setRoomWidth(26D);

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestIfRoomLengthGreaterThan33() throws Exception {
        roomRequestDTOMock.setRoomLength(34D);

        mockMvc.perform(post("/propriedade/determinaMetrosQuadradosComodo", propertyRequestDTOMock)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(propertyRequestDTOMock)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
