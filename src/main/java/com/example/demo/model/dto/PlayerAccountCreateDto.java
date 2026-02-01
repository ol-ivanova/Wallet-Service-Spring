package com.example.demo.model.dto;

import com.example.demo.model.domain.Transaction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PlayerAccountCreateDto {
    private UUID accountNumber;
    private BigDecimal balance;
}
