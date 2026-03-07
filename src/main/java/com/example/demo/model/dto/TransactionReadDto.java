package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Транзакции пользователя - для получения данных")
public class TransactionReadDto {
    @Schema(description = "ID транзакции")
    private UUID id;
    @Schema(description = "Время транзакции")
    private LocalDateTime createdDate;
    @Schema(description = "Тип транзакции")
    private TransactionType type;
    @Schema(description = "Сумма транзакции")
    private BigDecimal sum;
    @Schema(description = "От кого")
    private PlayerAccount playerAccountFrom;
    @Schema(description = "Кому")
    private PlayerAccount playerAccountTo;
}
