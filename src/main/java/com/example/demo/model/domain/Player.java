package com.example.demo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Сущность - пользователь
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "player")
@ToString(exclude = {"playerAccount", "audit"})
public class Player {
    /**
     * id, генерируемый при вставке экземпляра класса в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Имя пользователя
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Логин пользователя
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

//    private LocalDate birthDate;
//    private Integer age;

    //    private Birthday birthDate;
    /**
     * Пароль игрока
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Аккаунт пользователя
     */
    @OneToMany(mappedBy = "player", cascade = CascadeType.PERSIST)
    private List<PlayerAccount> playerAccount;

    /**
     * Аудит пользователя
     */
    @OneToMany(mappedBy = "player")
    private List<PlayerAudit> audit;
}
