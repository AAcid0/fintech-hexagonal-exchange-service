package com.global66practice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global66practice.controller.dto.CreateTraderResponseDTO;
import com.global66practice.controller.dto.GetTraderWalletResponseDTO;
import com.global66practice.controller.dto.RequestCreateTraderDTO;
import com.global66practice.domain.exceptions.IncompleteInformationException;
import com.global66practice.domain.models.Trader;
import com.global66practice.service.interfaces.ITraderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/traders")
public class TraderController {

    private final ITraderService traderService;

    public TraderController(ITraderService traderService) {
        this.traderService = traderService;
    }

    @PostMapping
    public ResponseEntity<CreateTraderResponseDTO> createTrader(
            @Valid @RequestBody RequestCreateTraderDTO requestCreateTraderDTO) {
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

        CreateTraderResponseDTO result = CreateTraderResponseDTO.builder()
                .id(traderSaved.getId())
                .balanceUSD(traderSaved.getPocket().getBalanceUSD())
                .balanceMAR(traderSaved.getPocket().getBalanceMAR())
                .balanceVEN(traderSaved.getPocket().getBalanceVEN())
                .build();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{traderId}/wallet")
    public ResponseEntity<GetTraderWalletResponseDTO> getTraderWallet(@PathVariable("traderId") UUID traderId) {
        Trader trader = traderService.getTraderWallet(traderId.toString());

        GetTraderWalletResponseDTO result = GetTraderWalletResponseDTO.builder()
                .name(trader.getName())
                .balanceUSD(trader.getPocket().getBalanceUSD())
                .balanceMAR(trader.getPocket().getBalanceMAR())
                .balanceVEN(trader.getPocket().getBalanceVEN())
                .build();

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Trader>> getAllTraders() {
        List<Trader> traders = traderService.getAllTraders();
        return ResponseEntity.ok(traders);
    }
}
