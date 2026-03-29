package com.example.demo.service;

import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.domain.Player;
import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerReadDto;

import com.example.demo.model.enums.AuditAction;
import com.example.demo.model.exception.PlayerException;
import com.example.demo.repository.PlayerAuditRepository;
import com.example.demo.repository.PlayerRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerAccountService playerAccountService;
    private final PlayerMapper playerMapper;
    private final Session session;
    private final EntityManager entityManager;
    private final PlayerAuditRepository playerAuditRepository;
    private final TestService testService;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional
    public void test(){
//        Player player = Player.builder()
//                .id(3)
//                .name("Artur")
//                .username("test")
//                .password("test")
//                .build();
//        Player player1 = entityManager.merge(player);

//        Player artur = playerRepository.findById(3).get();
//        artur.setName("Artur1");
//        artur.setUsername("test123");
//        entityManager.refresh(artur);
//        System.out.println();

//        Player player = Player.builder()
//                .name("test name")
//                .username("test username 123")
//                .password("123")
//                .build();
//
//        PlayerAudit playerAudit = PlayerAudit.builder()
//                .action(AuditAction.LOGIN)
//                .dateTime(LocalDateTime.now())
//                .build();
//
////        player.setAudit(List.of(playerAudit));
//        playerAudit.setPlayer(player);
//
//        playerAuditRepository.save(playerAudit);

        /**
         * player подгружается лениво (ленивая загрузка, т.е. по необходимости) в playerAudit
         * т.е. запрос на player выполнится только тогда, когда будет обращаться в коде к player,
         * важно, нам нужен не сам player, а его поля, поэтому запрос будет происходить только при обращении к полям player
         * через прокси реализуется механизм ленивой загрузки, т.е. когда мы ставим LAZY над сущностью (player),
         * это означает, что в player будет хранится прокси объект на Player, поэтому
         * getPlayer() будет возвращать прокси, привязанный к Player, а прокси в себе хранит id сущности, по которой
         * в дальнейшем запросит всю сущностью полностью при обращении к одному из полей
         * getId() также не трегирит запрос, потому что прокси и так хранит в себе id сущности, по которой он
         * в дальнейшем запросит всю сущностью
         */
//        PlayerAudit playerAudit = playerAuditRepository.findById(8).get();
//        playerAudit.getPlayer();

/*        Player player = playerRepository.findById(16).get();
        List<PlayerAudit> audit = player.getAudit();*/
//        PlayerAudit first = audit.getFirst();

//        Optional<Player> testName = playerRepository.findByUsername("test username 123");
//        System.out.println();

//        Map<String, Object> params = new HashMap<>();
//        params.put("username", "test username 123");
//        List<Player> players = jdbcTemplate.query(
//                "SELECT * FROM player WHERE username = :username" ,
//                params ,
//                (rs , rowNum) -> {
//                    return Player.builder()
//                            .id(rs.getInt("id"))
//                            .name(rs.getString("name"))
//                            .username(rs.getString("username"))
//                            .password(rs.getString("password"))
//                            .build();
//                }
//        );
//        System.out.println(players);

        PlayerAudit playerAudit = playerAuditRepository.findById(8).get();
        playerAudit.getPlayer().getUsername();
    }

    /**
     * только findById реально кешируется в PC, все остальные запросы (ex findByGuid, findByUsername и т д)- нет,
     * но при этом они также попадают в PC, т.е. будут проассоциированы с сессией и можно производить какие-либо манипуляции с ними
     */
    public Player findById(int id){
        playerRepository.findByUsername("olya");
        playerRepository.findByUsername("olya");

        return playerRepository.findById(id).get();
    }

    /**
     * Метод для создания пользователя
     * @param playerCreateDto - dto объект класса PlayerCreateDto
     * @return - dto объект класса PlayerReadDto
     */
    @Transactional
    public PlayerReadDto createPlayer(PlayerCreateDto playerCreateDto){
        Player player = playerMapper.dtoToPlayer(playerCreateDto);
        if(playerRepository.findByUsername(player.getUsername()).isPresent()){
            throw new PlayerException("Такой логин уже используется");
        }

        Player savedPlayer = playerRepository.save(player);
        PlayerAccountReadDto playerAccountReadDto = playerAccountService.createPlayerAccount(savedPlayer);
        PlayerReadDto playerReadDto = playerMapper.playerToDto(savedPlayer);
        playerReadDto.getPlayerAccounts().add(playerAccountReadDto);

        return playerReadDto;
    }

    /**
     * Метод для поиска пользователя по логину и паролю
     * @param username - логин пользователя
     * @param password - пароль пользователя
     * @return - dto объект класса PlayerReadDto
     */
    public PlayerReadDto findByCredentials(String username, String password){
        return playerRepository.findByUsernameAndPassword(username, password)
                .map(player -> playerMapper.playerToDto(player))
                .orElseThrow(() -> new PlayerException("Пользователь не найден"));
    }

    /**
     * Метод для обновления пользователя
     * @param id - id пользователя
     * @param playerCreateDto - dto объект класса PlayerCreateDto, содержащий новую информацию
     * @return - dto объект класса PlayerReadDto
     */
    @Transactional
    public PlayerReadDto updatePlayer(Integer id, PlayerCreateDto playerCreateDto){
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerException("Пользователь не найден"));

        player.setName(playerCreateDto.getName());
        player.setUsername(playerCreateDto.getUsername());
        player.setPassword(playerCreateDto.getPassword());

        Player updatedPlayer = playerRepository.save(player);

        return playerMapper.playerToDto(updatedPlayer);
    }

    @Transactional
    public void updatePlayerTest(Integer id, PlayerCreateDto playerCreateDto){
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerException("Пользователь не найден"));
        log.info("isDirty: {}", session.isDirty());
        player.setName(playerCreateDto.getName());
        player.setUsername(playerCreateDto.getUsername());
        player.setPassword(playerCreateDto.getPassword());

        log.info("isDirty: {}", session.isDirty());

        playerRepository.flush();
        log.info("message1");
//        Player updatedPlayer = playerRepository.save(player);

//        return playerMapper.playerToDto(updatedPlayer);
    }

    /**
     * Метод для удаления пользователя
     * @param id - id пользователя
     */
    @Transactional
    public void deletePlayerById(int id){
        playerRepository.deleteById(id);
    }
}
