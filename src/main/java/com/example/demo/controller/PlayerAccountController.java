package com.example.demo.controller;
import com.example.demo.model.dto.PlayerAccountCreateDto;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.model.dto.TransactionCreateDto;
import com.example.demo.service.PlayerAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/player-account", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name ="Аккаунт пользователя", description = "Операции управления аккаунтом пользователя")
public class PlayerAccountController {
    private final PlayerAccountService playerAccountService;

    @Operation(summary = "Создание аккаунта пользователя")
    @PostMapping
    public PlayerAccountReadDto createPlayerAccount(@RequestBody PlayerAccountCreateDto playerAccountCreateDto){
        return playerAccountService.createPlayerAccount(playerAccountCreateDto);
    }

    @Operation(summary = "Поиск аккаунта пользователя")
    @GetMapping
    public PlayerAccountReadDto findPlayerAccountByNumber(
            @Parameter(description = "Номер аккаунта")
            @RequestParam UUID accountNumber){
        return playerAccountService.findPlayerAccountByAccountNumber(accountNumber);
    }

    @Operation(summary = "Обновление аккаунта пользователя")
    @PutMapping("/{accountNumber}")
    public PlayerAccountReadDto updatePlayerAccount(
            @Parameter(description = "Номер аккаунта")
            @PathVariable UUID accountNumber,
            @RequestBody PlayerAccountCreateDto playerAccountCreateDto){
        return playerAccountService.updatePlayerAccount(accountNumber, playerAccountCreateDto);
    }

    @Operation(summary = "Удаление аккаунта пользователя")
    @DeleteMapping("/{accountNumber}")
    public void deletePlayerAccount(
            @Parameter(description = "Номер аккаунта")
            @PathVariable UUID accountNumber){
        playerAccountService.deletePlayerAccountByAccountNumber(accountNumber);
    }

    @Operation(summary = "Кредит операция")
    @PutMapping("/credit")
    public void doCreditOperation(@RequestBody TransactionCreateDto transactionCreateDto){
        playerAccountService.doCreditOperation(transactionCreateDto);
    }

    @Operation(summary = "Дебит операция")
    @PutMapping("/debit")
    public void doDebitOperation(@RequestBody TransactionCreateDto transactionCreateDto){
        playerAccountService.doDebitOperation(transactionCreateDto);
    }
}
