package com.example.demo.service;

import com.example.demo.mapper.PlayerAuditMapper;
import com.example.demo.model.domain.Player;
import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAuditCreateDto;
import com.example.demo.model.dto.PlayerAuditReadDto;
import com.example.demo.repository.PlayerAccountRepository;
import com.example.demo.repository.PlayerAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerAuditService {
    private final PlayerAuditRepository playerAuditRepository;
    private final PlayerAuditMapper playerAuditMapper;

    @Transactional
    public void createAudit(PlayerAuditCreateDto playerAuditCreateDto){
       playerAuditRepository.save(playerAuditMapper.dtoToPlayerAudit(playerAuditCreateDto));
    }

    public List<PlayerAudit> findAll(){
        return playerAuditRepository.findAll();
    }

    public List<PlayerAudit> findAuditByPlayerId(int playerId){
        PlayerAudit playerAudit1 = playerAuditRepository.findById(playerId).get();
        return List.of(playerAudit1);
    }
}
