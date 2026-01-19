package com.global66practice.service.interfaces;

import java.util.List;

import com.global66practice.domain.models.Trader;

public interface ITraderService {
    Trader createTrader(Trader trader);

    Trader getTraderWallet(String traderId);

    List<Trader> getAllTraders();
}
