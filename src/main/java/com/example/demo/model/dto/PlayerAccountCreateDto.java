package com.example.demo.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Аккаунт пользователя - для создания записи")
public class PlayerAccountCreateDto {
    @Schema(description = "Баланс")
    private BigDecimal balance;
    @Schema(description = "ID пользователя")
    private Integer playerId;
}
