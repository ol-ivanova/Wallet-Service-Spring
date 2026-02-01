package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReadDto {
    private UUID id;
    private LocalDateTime createdDate;
    private TransactionType type;
    private BigDecimal sum;
    private PlayerAccount playerAccountFrom;
    private PlayerAccount playerAccountTo;
}
