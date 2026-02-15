package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerCreateDto {
    private String name;
    private String username;
    private String password;
}
