package com.example.demo.model.params;

import com.example.demo.model.dto.PlayerReadDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Ответ на запрос с пагинацией")
public class PageableResult<T> {
    @Schema(description = "Данные")
    private T data;

    @Schema(description = "Смещение")
    private int offset;

    @Schema(description = "Количество")
    private int limit;

    @Schema(description = "Общее количество")
    private long total;
}
