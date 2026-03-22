package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Сущность - аккаунт пользователя
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "player_account")
public class PlayerAccount {
    /**
     * Номер счета, генерируемый при вставке экземпляра класса в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountNumber;

    /**
     * Баланс счета
     */
    @Column(name = "balance")
    private BigDecimal balance;

    /**
     * id пользователя, к которому привязан аккаунт
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "player_id", insertable = false, updatable = false)
    private Player player;
    @Column(name = "player_id")
    private Integer playerId;

    /**
     * Транзакции счета. Лист отправителей
     */
    @OneToMany (mappedBy = "playerAccountFrom")
    private List<Transaction> transactionsFrom;

    /**
     * Транзакции счета. Лист получателей
     */
    @OneToMany (mappedBy = "playerAccountTo")
    private List<Transaction> transactionsTo;
}
