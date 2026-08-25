package com.example.demo.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerProjectionByClass {
    @Schema(description = "ID пользователя")
    private Integer id;
    @Schema(description = "Имя пользователя")
    private String name;
    @Schema(description = "Логин пользователя")
    private String username;
}
