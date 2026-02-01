package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCreateDto {
    private String name;
    private String username;
    private String password;
}
