package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "player")
@Entity
@Data
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
    /**
     * Пароль игрока
     */
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
    private List<PlayerAccount> playerAccount;

    @OneToMany(mappedBy = "player")
    private List<PlayerAudit> audit;
}
