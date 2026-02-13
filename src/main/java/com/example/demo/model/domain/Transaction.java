package com.example.demo.model.domain;

import com.example.demo.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "transaction")
@Entity
public class Transaction {
    /**
     * id, генерируемый при вставке экземпляра класса в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Время транзакции, генерируемое при вставке экземпляра класса в БД
     */
    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;
    /**
     * Тип транзакции
     */
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    /**
     * Сумма транзакции
     */
    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    /**
     * Счет отправителя
     */
    @ManyToOne
    @JoinColumn(name = "player_account_from", insertable = false, updatable = false)
    private PlayerAccount accountFrom;
    @Column(name = "player_account_from")
    private UUID playerAccountFrom;

    /**
     * Счет получателя
     */
    @ManyToOne
    @JoinColumn(name = "player_account_to", insertable = false, updatable = false)
    private PlayerAccount accountTo;
    @Column(name = "player_account_to")
    private UUID playerAccountTo;
}
