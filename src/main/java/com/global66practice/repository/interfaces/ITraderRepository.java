package com.global66practice.repository.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.global66practice.domain.models.Trader;

public interface ITraderRepository {
    Trader save(Trader trader);

    List<Trader> findAll();

    Optional<Trader> findById(UUID id);
}
