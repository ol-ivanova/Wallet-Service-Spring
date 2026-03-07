package com.example.demo.service;

import com.example.demo.mapper.TransactionMapper;
import com.example.demo.model.domain.Transaction;
import com.example.demo.model.dto.TransactionCreateDto;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    /**
     * Метод для фиксирования информации о транзакции
     * @param transactionCreateDto - dto объект класса TransactionCreateDto
     */
    @Transactional
    public void transferData(TransactionCreateDto transactionCreateDto){
        transactionRepository.save(transactionMapper.dtoToTransaction(transactionCreateDto));
    }

    /**
     * Метод для получения истории транзакций по номеру счета
     * @param accountNumber - номер счета
     * @return - список транзакций
     */
    public List<Transaction> getTransactionHistoryByNumberAccount(UUID accountNumber) {
        return transactionRepository.findTransactionByAccountNumber(accountNumber);
    }

}
