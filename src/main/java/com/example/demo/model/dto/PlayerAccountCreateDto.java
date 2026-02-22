package com.example.demo.model.dto;

import com.example.demo.model.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlayerAccountCreateDto {
    private BigDecimal balance;
    private Integer playerId;
}
