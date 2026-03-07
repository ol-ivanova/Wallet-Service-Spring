package com.example.demo.controller;

import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.service.PlayerAuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player-audit")
@RequiredArgsConstructor
@Tag(name ="Аудит пользователя", description = "Операции над аудитом пользователя")
public class PlayerAuditController {
    private final PlayerAuditService playerAuditService;

    @Operation(summary = "Получение аудита пользователя")
    @GetMapping
    public List<PlayerAudit> findAuditByPlayerId(
            @Parameter(description = "ID пользователя")
            Integer id){
        return playerAuditService.findAuditByPlayerId(id);
    }
}
