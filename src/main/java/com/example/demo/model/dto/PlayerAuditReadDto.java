package com.example.demo.model.dto;

import com.example.demo.model.domain.Player;
import com.example.demo.model.enums.AuditAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerAuditReadDto {
    private Integer id;
    private LocalDateTime dateTime;
    private AuditAction action;
    private Player player;
}
