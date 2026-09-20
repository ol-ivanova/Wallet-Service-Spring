package com.example.demo.service;

import com.example.demo.mapper.PlayerAuditMapper;
import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAuditEvent;
import com.example.demo.model.dto.PlayerAuditReadDto;
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

    /**
     * Метод для создания аудита пользователя
     * @param audit - объект класса PlayerAuditEvent
     */
    @Transactional
    public void createAudit(PlayerAuditEvent audit){
       playerAuditRepository.save(playerAuditMapper.dtoToDomain(audit));
    }

    /**
     * Метод для поиска аудита по id пользователя
     * @param playerId - id пользователя
     * @return - список аудита
     */
    public List<PlayerAudit> findAuditByPlayerId(int playerId){
        PlayerAudit playerAudit = playerAuditRepository.findById(playerId).get();
        return List.of(playerAudit);
    }

    /**
     * Метод для поиска аудита всех пользователей
     * @return - список аудита
     */
    public List<PlayerAuditReadDto> findAll() {
        List<PlayerAudit> playerAudits = playerAuditRepository.findAll();
        List<PlayerAuditReadDto> playerAuditReadDtos = playerAuditMapper.domainsToDtos(playerAudits);
        return playerAuditReadDtos;
    }
}
