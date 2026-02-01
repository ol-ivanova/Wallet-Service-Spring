package com.example.demo.repository;

import com.example.demo.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findByUsername(String username);
    Optional<Player> findByUsernameAndPassword(String username, String password);
}
