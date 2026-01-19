package com.global66practice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.global66practice.domain.exceptions.TraderNotFoundException;
import com.global66practice.domain.models.Trader;
import com.global66practice.repository.interfaces.ITraderRepository;
import com.global66practice.service.interfaces.ITraderService;
import com.global66practice.service.strategy.HumanStrategy;
import com.global66practice.service.strategy.MartianStrategy;
import com.global66practice.service.strategy.VenusianStrategy;
import com.global66practice.service.strategy.interfaces.IExchangeStrategy;

@Service
public class TraderServiceImp implements ITraderService {
    private final ITraderRepository traderRepository;
    private IExchangeStrategy exchangeStrategy;

    public TraderServiceImp(ITraderRepository traderRepository) {
        this.traderRepository = traderRepository;
        this.exchangeStrategy = null;
    }

    @Override
    public Trader createTrader(Trader trader) {
        setExchangeStrategy(trader);
        trader.setPocket(exchangeStrategy.generateSignInBonus());

        return traderRepository.save(trader);
    }

    @Override
    public List<Trader> getAllTraders() {
        return traderRepository.findAll();
    }

    @Override
    public Trader getTraderWallet(String traderId) {
        Optional<Trader> trader = traderRepository.findById(UUID.fromString(traderId));

        if (trader.isEmpty()) {
            throw new TraderNotFoundException("El usuario no fue encontrado en el sistema de divisas.");
        }

        return trader.get();
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
}
