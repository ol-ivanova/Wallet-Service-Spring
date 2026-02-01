package com.example.demo.mapper;

import com.example.demo.model.domain.Transaction;
import com.example.demo.model.dto.TransactionCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {
    Transaction dtoToDomain(TransactionCreateDto transactionCreateDto);
}
