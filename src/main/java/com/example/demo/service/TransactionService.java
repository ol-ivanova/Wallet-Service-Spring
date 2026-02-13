package com.example.demo.service;

import com.example.demo.mapper.TransactionMapper;
import com.example.demo.model.domain.Transaction;
import com.example.demo.model.dto.TransactionCreateDto;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Transactional
    public void transferData(TransactionCreateDto transactionCreateDto){
        transactionRepository.save(transactionMapper.dtoToDomain(transactionCreateDto));
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionHistoryByNumberAccount(Long accountNumber) {
        return transactionRepository.findTransactionByAccountNumber(accountNumber);
    }

}
