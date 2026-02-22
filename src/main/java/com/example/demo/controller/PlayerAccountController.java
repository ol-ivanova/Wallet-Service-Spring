package com.example.demo.controller;

import com.example.demo.model.domain.Player;
import com.example.demo.model.dto.PlayerAccountCreateDto;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.service.PlayerAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.UUID;

@Controller
@RequestMapping(value = "/api/v1/player-account", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PlayerAccountController {
    private final PlayerAccountService playerAccountService;

    @PostMapping
    public PlayerAccountReadDto createPlayerAccount(@RequestBody PlayerAccountCreateDto playerAccountCreateDto){
        return playerAccountService.createPlayerAccount(playerAccountCreateDto);
    }

    @GetMapping
    public PlayerAccountReadDto findPlayerAccountByNumber(@RequestParam UUID accountNumber){
        return playerAccountService.findPlayerAccountByAccountNumber(accountNumber);
    }

    @PutMapping("/{accountNumber}")
    public PlayerAccountReadDto updatePlayerAccount(@PathVariable UUID accountNumber,
                                                    @RequestBody PlayerAccountCreateDto playerAccountCreateDto){
        return playerAccountService.updatePlayerAccount(accountNumber, playerAccountCreateDto);
    }

    @DeleteMapping("/{accountNumber}")
    public void deletePlayerAccount(@PathVariable UUID accountNumber){
        playerAccountService.deletePlayerAccountByAccountNumber(accountNumber);
    }
}
