package com.example.demo.repository;

import com.example.demo.model.domain.Player;
import com.example.demo.model.domain.PlayerAudit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerAuditRepository extends JpaRepository<PlayerAudit, Integer> {
//    @Override
//    @EntityGraph(attributePaths = "player")
//    Optional<PlayerAudit> findById(Integer integer);

    List<PlayerAudit> findAllByPlayer(Player player);

    List<PlayerAudit> findAllByPlayerId(Integer playerId);
}
