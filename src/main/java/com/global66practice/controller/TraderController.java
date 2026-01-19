package com.global66practice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global66practice.controller.dto.RequestCreateTraderDTO;
import com.global66practice.domain.exceptions.IncompleteInformationException;
import com.global66practice.domain.models.Trader;
import com.global66practice.service.TraderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/traders")
public class TraderController {

    private final TraderService traderService;

    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    @PostMapping
    public ResponseEntity<Trader> createTrader(@Valid @RequestBody RequestCreateTraderDTO requestCreateTraderDTO) {
        if (requestCreateTraderDTO.getName() == null || requestCreateTraderDTO.getName().isEmpty()) {
            throw new IncompleteInformationException("Name is required");
        }

        if (requestCreateTraderDTO.getSpecie() == null || requestCreateTraderDTO.getSpecie().isEmpty()) {
            throw new IncompleteInformationException("Specie is required");
        }

        if (!requestCreateTraderDTO.getSpecie().toUpperCase().equals("HUMAN")
                && !requestCreateTraderDTO.getSpecie().toUpperCase().equals("MARTIAN")
                && !requestCreateTraderDTO.getSpecie().toUpperCase().equals("VENUSIAN")) {
            throw new IncompleteInformationException("Specie must be HUMAN, MARTIAN or VENUSIAN");
        }

        Trader trader = Trader.builder()
                .name(requestCreateTraderDTO.getName().toUpperCase())
                .specie(requestCreateTraderDTO.getSpecie().toUpperCase())
                .build();

        Trader traderSaved = traderService.createTrader(trader);

        return ResponseEntity.ok(traderSaved);
    }

    @GetMapping
    public ResponseEntity<List<Trader>> getAllTraders() {
        List<Trader> traders = traderService.getAllTraders();
        return ResponseEntity.ok(traders);
    }
}
