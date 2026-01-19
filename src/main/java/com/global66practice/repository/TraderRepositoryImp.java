package com.global66practice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.global66practice.domain.models.Trader;
import com.global66practice.repository.interfaces.ITraderRepository;

@Repository
public class TraderRepositoryImp implements ITraderRepository {
    private final ConcurrentHashMap<UUID, Trader> traders = new ConcurrentHashMap<>();

    @Override
    public Trader save(Trader trader) {
        if (trader.getId() == null) {
            trader.setId(UUID.randomUUID());
        }

        traders.put(trader.getId(), trader);
        return trader;
    }

    @Override
    public List<Trader> findAll() {
        return traders.values().stream().toList();
    }

    @Override
    public Optional<Trader> findById(UUID id) {
        return Optional.ofNullable(traders.get(id));
    }
}
