package com.example.demo.model.dto;

import com.example.demo.model.domain.Player;
import com.example.demo.model.domain.Transaction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Аккаунт пользователя- для получения данных")
public class PlayerAccountReadDto {
    @Schema(description = "Номер аккаунта")
    private UUID accountNumber;
    @Schema(description = "Баланс")
    private BigDecimal balance;
}
