package com.global66practice.domain.models;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pocket {
    private BigDecimal balanceUSD;
    private BigDecimal balanceMars;
    private BigDecimal balanceVenus;
}
