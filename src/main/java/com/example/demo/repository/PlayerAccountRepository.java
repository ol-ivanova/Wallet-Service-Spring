package com.example.demo.repository;

import com.example.demo.model.domain.PlayerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerAccountRepository extends JpaRepository<PlayerAccount, UUID> {
    /**
     * Метод для поиска аккаунт пользователя по номеру счета
     * @param accountNumber - номер счета
     * @return - сущность класса PlayerAccount
     */
    @Query(nativeQuery = true, value = """
        SELECT * FROM player_account WHERE account_number = :accountNumber;
    """)
     PlayerAccount findPlayerAccountByAccountNumber(UUID accountNumber);
}
