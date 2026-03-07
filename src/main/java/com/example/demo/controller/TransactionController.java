package com.example.demo.controller;

import com.example.demo.model.domain.Transaction;
import com.example.demo.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
@Tag(name ="Транзакции пользователя", description = "Операции над транзакциями пользователя")
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "Получение транзакций пользователя")
    @GetMapping
    public List<Transaction> findTransactionHistoryByNumberAccount(
            @Parameter(description = "Номер аккаунта")
            UUID accountNumber){
        return transactionService.getTransactionHistoryByNumberAccount(accountNumber);
    }
}
