package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транзакции пользователя - для создания записи")
public class TransactionCreateDto {
    @Schema(description = "Тип транзакции")
    private TransactionType type;
    @Schema(description = "Сумма транзакции")
    private BigDecimal sum;
    @Schema(description = "От кого")
    private UUID playerAccountFrom;
    @Schema(description = "Кому")
    private UUID playerAccountTo;
}
