package com.example.demo.listener;

import com.example.demo.model.dto.PlayerAuditEvent;
import com.example.demo.service.PlayerAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditEventsListener {
    private final PlayerAuditService playerAuditService;

    @EventListener(PlayerAuditEvent.class)
    private void handleCreateAudit(PlayerAuditEvent audit) {
        playerAuditService.createAudit(audit);
    }
}
