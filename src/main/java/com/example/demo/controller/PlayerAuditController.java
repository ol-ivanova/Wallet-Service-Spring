package com.example.demo.controller;

import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.service.PlayerAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/player-audit")
@RequiredArgsConstructor
public class PlayerAuditController {
    private final PlayerAuditService playerAuditService;

    @GetMapping
    public List<PlayerAudit> findAuditByPlayerId(Integer id){
        return playerAuditService.findAuditByPlayerId(1);
    }
}
