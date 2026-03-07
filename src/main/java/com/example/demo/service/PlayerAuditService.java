package com.example.demo.service;

import com.example.demo.mapper.PlayerAuditMapper;
import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAuditCreateDto;
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
     * @param playerAuditCreateDto - dto объект класса PlayerAuditCreateDto
     */
    @Transactional
    public void createAudit(PlayerAuditCreateDto playerAuditCreateDto){
       playerAuditRepository.save(playerAuditMapper.dtoToPlayerAudit(playerAuditCreateDto));
    }

    /**
     * Метод для поиска аудита по id пользователя
     * @param playerId - id пользователя
     * @return - список аудита
     */
    public List<PlayerAudit> findAuditByPlayerId(int playerId){
        PlayerAudit playerAudit1 = playerAuditRepository.findById(playerId).get();
        return List.of(playerAudit1);
    }
}
