package com.example.demo.controller;

import com.example.demo.model.domain.Transaction;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> findTransactionHistoryByNumberAccount(UUID accountNumber){
        return transactionService.getTransactionHistoryByNumberAccount(accountNumber);
    }
}
