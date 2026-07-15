package com.example.demo.controller;

import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.model.dto.PlayerAuditReadDto;
import com.example.demo.service.PlayerAuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/player-audit", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name ="Аудит пользователя", description = "Операции над аудитом пользователя")
public class PlayerAuditController {
    private final PlayerAuditService playerAuditService;

    @GetMapping
    @Operation(summary = "Получение аудита всех пользовотелей")
    public List<PlayerAuditReadDto> findAll() {
        return playerAuditService.findAll();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Получение аудита пользователя")
    public List<PlayerAudit> findAuditByPlayerId(
            @Parameter(description = "ID пользователя") @PathVariable Integer id){
        return playerAuditService.findAuditByPlayerId(id);
    }
}
