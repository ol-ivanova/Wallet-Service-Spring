package com.example.demo.mapper;

import com.example.demo.model.domain.Player;
import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerMapper {
    Player dtoToPlayer(PlayerCreateDto playerCreateDto);
    PlayerReadDto playerToDto(Player player);
}
