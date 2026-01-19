package com.global66practice.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTraderResponseDTO {
    private UUID id;
    private BigDecimal balanceUSD;
    private BigDecimal balanceMAR;
    private BigDecimal balanceVEN;
}
