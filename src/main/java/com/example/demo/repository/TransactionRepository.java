package com.example.demo.repository;

import com.example.demo.model.domain.Player;
import com.example.demo.model.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    
    List<Transaction> findTransactionByAcc(Long numberAccount);
}
