package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Пользователь - для создания записи")
public class PlayerCreateDto {
    @Schema(description = "Имя пользователя")
    private String name;
    @Schema(description = "Логин пользователя")
    private String username;
    @Schema(description = "Пароль пользователя")
    private String password;
}
