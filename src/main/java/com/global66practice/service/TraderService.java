package com.global66practice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global66practice.domain.models.Trader;
import com.global66practice.repository.interfaces.ITraderRepository;
import com.global66practice.service.strategy.HumanStrategy;
import com.global66practice.service.strategy.MartianStrategy;
import com.global66practice.service.strategy.VenusianStrategy;
import com.global66practice.service.strategy.interfaces.IExchangeStrategy;

@Service
public class TraderService {
    private final ITraderRepository traderRepository;
    private IExchangeStrategy exchangeStrategy;

    public TraderService(ITraderRepository traderRepository) {
        this.traderRepository = traderRepository;
        this.exchangeStrategy = null;
    }

    private void setExchangeStrategy(Trader trader) {
        if (trader.getSpecie().equals("HUMAN")) {
            this.exchangeStrategy = new HumanStrategy();
        }

        if (trader.getSpecie().equals("MARTIAN")) {
            this.exchangeStrategy = new MartianStrategy();
        }

        if (trader.getSpecie().equals("VENUSIAN")) {
            this.exchangeStrategy = new VenusianStrategy();
        }
    }

    public Trader createTrader(Trader trader) {
        setExchangeStrategy(trader);
        trader.setPocket(exchangeStrategy.generateSignInBonus());

        return traderRepository.save(trader);
    }

    public List<Trader> getAllTraders() {
        return traderRepository.findAll();
    }
}
