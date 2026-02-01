package com.example.demo.model.dto;

import com.example.demo.model.domain.Player;
import com.example.demo.model.enums.AuditAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerAuditCreateDto {
    private AuditAction action;
    private Player player;
}
