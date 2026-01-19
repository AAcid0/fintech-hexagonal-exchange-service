package com.global66practice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.global66practice.domain.models.Pocket;
import com.global66practice.domain.models.Trader;
import com.global66practice.service.interfaces.ITraderService;

@WebMvcTest(TraderController.class)
@DisplayName("TraderController - getAllTraders Endpoint Tests")
class TraderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITraderService traderService;

    private Trader trader1;
    private Trader trader2;
    private Trader trader3;

    @BeforeEach
    void setUp() {
        // Create test data
        Pocket pocket1 = Pocket.builder()
                .balanceUSD(new BigDecimal("1000"))
                .balanceMAR(new BigDecimal("0"))
                .balanceVEN(new BigDecimal("0"))
                .build();

        trader1 = Trader.builder()
                .id(UUID.randomUUID())
                .name("JOHN DOE")
                .specie("HUMAN")
                .pocket(pocket1)
                .build();

        Pocket pocket2 = Pocket.builder()
                .balanceUSD(new BigDecimal("0"))
                .balanceMAR(new BigDecimal("500"))
                .balanceVEN(new BigDecimal("0"))
                .build();

        trader2 = Trader.builder()
                .id(UUID.randomUUID())
                .name("ZORG")
                .specie("MARTIAN")
                .pocket(pocket2)
                .build();

        Pocket pocket3 = Pocket.builder()
                .balanceUSD(new BigDecimal("0"))
                .balanceMAR(new BigDecimal("0"))
                .balanceVEN(new BigDecimal("750"))
                .build();

        trader3 = Trader.builder()
                .id(UUID.randomUUID())
                .name("VENUS")
                .specie("VENUSIAN")
                .pocket(pocket3)
                .build();
    }

    @Test
    @DisplayName("Should return list of all traders with HTTP 200")
    void testGetAllTraders_Success() throws Exception {
        // Arrange
        List<Trader> traders = Arrays.asList(trader1, trader2, trader3);
        when(traderService.getAllTraders()).thenReturn(traders);

        // Act & Assert
        mockMvc.perform(get("/api/v1/traders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].name").value("JOHN DOE"))
                .andExpect(jsonPath("$[0].specie").value("HUMAN"))
                .andExpect(jsonPath("$[0].pocket.balanceUSD").value(1000))
                .andExpect(jsonPath("$[1].name").value("ZORG"))
                .andExpect(jsonPath("$[1].specie").value("MARTIAN"))
                .andExpect(jsonPath("$[1].pocket.balanceMAR").value(500))
                .andExpect(jsonPath("$[2].name").value("VENUS"))
                .andExpect(jsonPath("$[2].specie").value("VENUSIAN"))
                .andExpect(jsonPath("$[2].pocket.balanceVEN").value(750));
    }

    @Test
    @DisplayName("Should return empty list when no traders exist")
    void testGetAllTraders_EmptyList() throws Exception {
        // Arrange
        when(traderService.getAllTraders()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/v1/traders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("Should return single trader in list")
    void testGetAllTraders_SingleTrader() throws Exception {
        // Arrange
        List<Trader> traders = Collections.singletonList(trader1);
        when(traderService.getAllTraders()).thenReturn(traders);

        // Act & Assert
        mockMvc.perform(get("/api/v1/traders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("JOHN DOE"))
                .andExpect(jsonPath("$[0].specie").value("HUMAN"));
    }

    @Test
    @DisplayName("Should verify correct endpoint mapping")
    void testGetAllTraders_EndpointMapping() throws Exception {
        // Arrange
        when(traderService.getAllTraders()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/v1/traders"))
                .andExpect(status().isOk());
    }
}
