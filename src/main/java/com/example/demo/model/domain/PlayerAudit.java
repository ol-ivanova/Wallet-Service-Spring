package com.example.demo.model.domain;

import com.example.demo.model.enums.AuditAction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * Сущность - аудит пользователя
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "player_audit")
public class PlayerAudit {
    /**
     * id лога, генерируемый при вставке экземпляра класса в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Дата и время, генерируемое при вставке экземпляра класса в БД
     */
    @Column(name = "date_time")
    @CreatedDate
    private LocalDateTime dateTime;

    /**
     * Тип активности
     */
    @Enumerated(EnumType.STRING)
    private AuditAction action;

    /**
     * id пользователя, к которому привязана активность
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_id")
    @ToString.Exclude
    private Player player;
}