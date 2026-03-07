package com.example.demo.mapper;

import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.dto.PlayerAccountCreateDto;
import com.example.demo.model.dto.PlayerAccountReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Интерфейс-mapper для PlayerAccount
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerAccountMapper {
    /**
     * Метод для преобразования dto в сущность
     * @param playerAccountCreateDto - dto объект
     * @return - сущность класса PlayerAccount
     */
    PlayerAccount dtoToPlayerAccount(PlayerAccountCreateDto playerAccountCreateDto);

    /**
     * Метод для преобразования сущности в dto
     * @param playerAccount - сущность
     * @return - dto объект класса PlayerAccountReadDto
     */
    PlayerAccountReadDto playerAccountToDto(PlayerAccount playerAccount);
}
