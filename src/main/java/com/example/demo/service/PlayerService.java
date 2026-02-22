package com.example.demo.service;

import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.domain.Player;
import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerReadDto;

import com.example.demo.model.exception.PlayerException;
import com.example.demo.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerAccountService playerAccountService;
    private final PlayerMapper playerMapper;

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

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public PlayerReadDto findByCredentials(String username, String password){
        return playerRepository.findByUsernameAndPassword(username, password)
                .map(player -> playerMapper.playerToDto(player))
                .orElseThrow(() -> new PlayerException("Пользователь не найден"));
    }

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
    public void deletePlayerById(int id){
        playerRepository.deleteById(id);
    }
}
