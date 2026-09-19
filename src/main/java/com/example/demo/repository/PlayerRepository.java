package com.example.demo.repository;

import com.example.demo.model.domain.Player;
import com.example.demo.model.dto.PlayerProjectionByClass;
import com.example.demo.model.dto.PlayerProjectionByInterface;
import com.example.demo.model.dto.PlayerReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Optional<Player> findByUsername(String username);
}
