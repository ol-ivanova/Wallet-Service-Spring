package com.example.demo.model.dto;

import com.example.demo.model.domain.Player;
import com.example.demo.model.enums.AuditAction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Аудит пользователя - для получения данных")
public class PlayerAuditReadDto {
    @Schema(description = "ID активности")
    private Integer id;
    @Schema(description = "Время активности")
    private LocalDateTime dateTime;
    @Schema(description = "Тип активности")
    private AuditAction action;
    @Schema(description = "Пользователь")
    private Player player;
}
