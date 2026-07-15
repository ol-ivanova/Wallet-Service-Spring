package com.example.demo.model.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Параметры поиска")
public class PageableParams {
    @Schema(description = "Размер страницы")
    private Integer limit = 10;

    @Schema(description = "Указатель на страницу")
    private Integer offset = 0;

    @Schema(description = "Направление сортировки")
    private String direction;// = "ASC";

    @Schema(description = "Колонка, по которой произвести сортировку")
    private String sortColumn;
}