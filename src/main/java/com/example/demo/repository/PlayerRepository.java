package com.example.demo.repository;

import com.example.demo.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
//    Optional<Player> findByUsername(String username);

    /*
    @Query(nativeQuery = true, value = """
        SELECT * FROM player WHERE username = :username
    """)
    Optional<Player> findByUsername(String username);*/

    @Modifying
    @Query(nativeQuery = true, value = """
        update player set password = :password where id = :id
    """)
    int updatePlayer(int id, String password);

//    @Query("""
//        SELECT p FROM Player p WHERE p.username = :username
//    """)
//    Optional<Player> findByUsername(String username);

    Optional<Player> findByUsername(String username);
    Optional<Player> findByUsernameAndPassword(String username, String password);
}
