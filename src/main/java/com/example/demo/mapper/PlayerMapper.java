package com.example.demo.mapper;

import com.example.demo.model.domain.Player;
import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerReadDto;
import com.example.demo.util.PlayerMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Интерфейс-mapper для Player
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {PlayerAccountMapper.class, PlayerMapperUtil.class})
public interface PlayerMapper {
    /**
     * Метод для преобразования dto в сущность
     * @param playerCreateDto - dto объект
     * @return - сущность класса Player
     */
    @Mapping(target = "password", qualifiedByName = {"PlayerMapperUtil", "encodePassword"})
    Player dtoToPlayer(PlayerCreateDto playerCreateDto);

    /**
     * Метод для преобразования сущности в dto
     * @param player - сущность
     * @return - dto объект класса PlayerReadDto
     */
    PlayerReadDto playerToDto(Player player);

    List<PlayerReadDto> domainsToDtos(List<Player> players);
}
