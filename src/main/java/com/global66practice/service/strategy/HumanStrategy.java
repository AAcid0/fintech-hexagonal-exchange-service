package com.global66practice.service.strategy;

import java.math.BigDecimal;

import com.global66practice.domain.models.Pocket;
import com.global66practice.service.strategy.interfaces.IExchangeStrategy;

public class HumanStrategy implements IExchangeStrategy {

    @Override
    public Pocket generateSignInBonus() {

        return Pocket.builder()
                .balanceUSD(new BigDecimal("1000"))
                .balanceMAR(new BigDecimal("0"))
                .balanceVEN(new BigDecimal("0"))
                .build();
    }

}
