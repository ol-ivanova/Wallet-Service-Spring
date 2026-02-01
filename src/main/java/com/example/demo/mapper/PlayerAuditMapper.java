package com.example.demo.mapper;


import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAuditCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerAuditMapper {
    PlayerAudit dtoToPlayerAudit(PlayerAuditCreateDto playerAuditCreateDto);
}
