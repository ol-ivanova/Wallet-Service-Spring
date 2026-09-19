package com.example.demo.service;

import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.domain.Player;
import com.example.demo.model.dto.*;
import com.example.demo.exception.PlayerException;
import com.example.demo.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerAccountService playerAccountService;
    private final PlayerMapper playerMapper;
    private final PasswordEncoder passwordEncoder;

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
        Player player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new PlayerException("Пользователь не найден"));

        if (!passwordEncoder.matches(password, player.getPassword())) {
            throw new PlayerException("Пользователь не найден");
        }

        return playerMapper.playerToDto(player);
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

    /**
     * Метод для удаления пользователя
     * @param id - id пользователя
     */
    @Transactional
    public void deletePlayerById(int id){
        playerRepository.deleteById(id);
    }

    /**
     * Метод для поиска пользователя по логину
     * @param username - логин пользователя
     * @return - пользователя
     */
    public Optional<Player> findPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

}
