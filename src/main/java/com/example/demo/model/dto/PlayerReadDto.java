package com.example.demo.model.dto;

import com.example.demo.model.domain.PlayerAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PlayerReadDto {
    private Integer id;
    private String name;
    private String username;
    private String password;
    @Builder.Default
    private List<PlayerAccountReadDto> playerAccounts = new ArrayList<>();
}
