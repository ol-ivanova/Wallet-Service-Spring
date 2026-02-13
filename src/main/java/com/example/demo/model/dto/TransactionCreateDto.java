package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreateDto {
    private TransactionType type;
    private BigDecimal sum;
    private long playerAccountFrom;
    private long playerAccountTo;
}
