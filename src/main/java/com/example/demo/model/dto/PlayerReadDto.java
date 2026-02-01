package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerReadDto {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private PlayerAccountReadDto playerAccountReadDto;
}
