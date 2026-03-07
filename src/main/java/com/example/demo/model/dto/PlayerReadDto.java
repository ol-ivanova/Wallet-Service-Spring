package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Schema(description = "Пользователь - для получения данных")
public class PlayerReadDto {
    @Schema(description = "ID пользователя")
    private Integer id;
    @Schema(description = "Имя пользователя")
    private String name;
    @Schema(description = "Логин пользователя")
    private String username;
    @Schema(description = "Пароль пользователя")
    private String password;
    @Schema(description = "Аккаунт(ы) пользователя")
    @Builder.Default
    private List<PlayerAccountReadDto> playerAccounts = new ArrayList<>();
}
