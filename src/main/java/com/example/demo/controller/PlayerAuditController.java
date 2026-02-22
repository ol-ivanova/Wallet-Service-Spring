package com.example.demo.controller;

import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.service.PlayerAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api/v1/player-audit")
@RequiredArgsConstructor
public class PlayerAuditController {
    private final PlayerAuditService playerAuditService;

    @GetMapping
    public List<PlayerAudit> findAuditByPlayerId(Integer id){
        return playerAuditService.findAuditByPlayerId(1);
    }
}
