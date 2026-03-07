package com.example.demo.mapper;

import com.example.demo.model.domain.Player;
import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Интерфейс-mapper для Player
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = PlayerAccountMapper.class)
public interface PlayerMapper {
    /**
     * Метод для преобразования dto в сущность
     * @param playerCreateDto - dto объект
     * @return - сущность класса Player
     */
    Player dtoToPlayer(PlayerCreateDto playerCreateDto);

    /**
     * Метод для преобразования сущности в dto
     * @param player - сущность
     * @return - dto объект класса PlayerReadDto
     */
    PlayerReadDto playerToDto(Player player);
}
