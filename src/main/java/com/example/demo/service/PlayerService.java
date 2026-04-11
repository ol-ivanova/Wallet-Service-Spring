package com.example.demo.service;

import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.domain.Player;
import com.example.demo.model.domain.PlayerAudit;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerReadDto;

import com.example.demo.exception.PlayerException;
import com.example.demo.repository.PlayerAuditRepository;
import com.example.demo.repository.PlayerRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
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
     * если нет update запроса, но есть сеттер, то flush выполнится при коммите транзакции
     * если есть @Query перед сеттером, то перед выполнением @Query выполнится автоматический flush
     * также flush выполнеится, если руками его вызвать на сессии
     *
     * flush - отправляет запрос в БД, вписывает данные в поля, но фиксируются данные (или записываются) только
     * при коммите транзакции
     *
     * в postgres по умолчанию включен уровень READ COMMITTED
     *
     * clearAutomatically: true
     * findByUsername - не кеширует запросы, а постоянно идет в БД за ними
     * с помощью updatePlayer мы отправляем запрос, вписываем данные в БД, но не фиксируем их, т.е. данные в БД пока не изменятся
     * а второй findByUsername делает запрос именно в БД, но т.к. не произошел коммит транзакции, данные в БД не изменятся,
     * и запрос вернет те же данные
     *
     * вне зависимости от того, что любые запросе кроме findById не кешируются, все равно
     * запросы отличные от него выполняются, но сначала идут в кеш и если там нет, то идут в БД
     */
    @Transactional
    public void update(){
//        Player player = playerRepository.findById(1).get();
//        player.setUsername("new username123456");
//        playerRepository.updatePlayer(1, "123");
//        Player playerAgain = playerRepository.findById(1).get();
//        System.out.println();

        Player player = playerRepository.findByUsername("new username123456").get();
        playerRepository.updatePlayer(1, "753");
        Player playerAgain = playerRepository.findByUsername("new username123456").get();
        System.out.println();
    }

    @Transactional
    public void firstFind(){
        if (TransactionSynchronizationManager.isActualTransactionActive()){
            TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
            log.info("Transaction: isNew = {}, name = {}", transactionStatus.isNewTransaction(), transactionStatus.getTransactionName());
        } else {
            log.info("There is no active transaction");
        }

        Optional<Player> byId = playerRepository.findById(1);
//        playerAccountService.findPlayerAccountByAccountNumber(UUID.fromString("5637fe66-047c-41f3-b8db-9ae061926805"));
        secondFind();
    }

    /**
     * вне зависимости от настройки propagation транзакция в рамках одного сервиса, т.е. одного прокси, наследуется
     */
    @Transactional
    public void secondFind(){
        if (TransactionSynchronizationManager.isActualTransactionActive()){
            TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
            log.info("Transaction: isNew = {}, name = {}", transactionStatus.isNewTransaction(), transactionStatus.getTransactionName());
        } else {
            log.info("There is no active transaction");
        }
    }

    public void testNplusOne(){
        List<Player> player = playerRepository.findAll();
        for (Player p : player) {
            for (PlayerAudit pa : p.getAudit()){
                log.debug("{} - {}", pa.getId(), pa.getAction());
            }
        }
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
