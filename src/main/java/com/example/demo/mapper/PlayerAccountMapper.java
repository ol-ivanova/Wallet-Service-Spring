package com.example.demo.mapper;

import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.dto.PlayerAccountCreateDto;
import com.example.demo.model.dto.PlayerAccountReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerAccountMapper {
    PlayerAccount dtoToPlayerAccount(PlayerAccountCreateDto playerAccountCreateDto);
    PlayerAccountReadDto playerAccountToDto(PlayerAccount playerAccount);
}
