package com.global66practice.controller.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTraderWalletResponseDTO {
    private String name;
    private BigDecimal balanceUSD;
    private BigDecimal balanceMAR;
    private BigDecimal balanceVEN;
}
