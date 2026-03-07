package com.example.demo.mapper;

import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAuditCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Интерфейс-mapper для PlayerAudit
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerAuditMapper {
    /**
     * Метод для преобразования dto в сущность
     * @param playerAuditCreateDto - dto объект
     * @return - сущность класса PlayerAudit
     */
    PlayerAudit dtoToPlayerAudit(PlayerAuditCreateDto playerAuditCreateDto);
}
