package com.example.demo.mapper;

import com.example.demo.model.domain.Transaction;
import com.example.demo.model.dto.TransactionCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Интерфейс-mapper для Transaction
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {
    /**
     * Метод для преобразования dto в сущность
     * @param transactionCreateDto - dto объект
     * @return - объект класса Transaction
     */
    Transaction dtoToTransaction(TransactionCreateDto transactionCreateDto);
}
