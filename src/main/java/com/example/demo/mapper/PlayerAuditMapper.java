package com.example.demo.mapper;

import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.model.dto.PlayerAuditCreateDto;
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
     * @param playerAuditCreateDto - dto объект
     * @return - сущность класса PlayerAudit
     */
    PlayerAudit dtoToDomain(PlayerAuditCreateDto playerAuditCreateDto);
    PlayerAuditReadDto domainToDto(PlayerAudit playerAudit);
    List<PlayerAuditReadDto> domainsToDtos(List<PlayerAudit> playerAudit);
}
