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
    /**
     * @EntityGraph(attributePaths = "audit") говорит о том, что нужно связанные записи playerAudit подтягивать как left join
     * для предотвращения N+1 проблемы
     */
    @Override
    @EntityGraph(attributePaths = "audit")
    List<Player> findAll();
    //    Optional<Player> findByUsername(String username);

    @Query("SELECT p FROM Player p")
    Slice<Player> findSlice(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "audit")
    Page<Player> findAll(Pageable pageable);

    @Query(nativeQuery = false, value = """
        SELECT p.name as name, p.username as username FROM Player p
    """)
    List<PlayerProjectionByInterface> findAllByInterface();

    @Query(nativeQuery = false, value = """
        SELECT p.id, p.name, p.username FROM Player p
    """)
    List<PlayerProjectionByClass> findAllByClass();

    /*
    @Query(nativeQuery = true, value = """
        SELECT * FROM player WHERE username = :username
    """)
    Optional<Player> findByUsername(String username);
    */

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
