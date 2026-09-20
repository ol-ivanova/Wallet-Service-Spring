package com.example.demo.mapper;

import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAuditEvent;
import com.example.demo.model.dto.PlayerAuditReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Интерфейс-mapper для PlayerAudit
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = PlayerMapper.class)
public interface PlayerAuditMapper {
    /**
     * Метод для преобразования dto в сущность
     * @param audit - объект аудита
     * @return - сущность класса PlayerAudit
     */
    PlayerAudit dtoToDomain(PlayerAuditEvent audit);
    List<PlayerAuditReadDto> domainsToDtos(List<PlayerAudit> playerAudit);
}
