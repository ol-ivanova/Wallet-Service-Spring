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

import java.util.List;

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
        Player savedPlayer = playerRepository.save(playerMapper.dtoToPlayer(playerCreateDto));
        PlayerAccountReadDto playerAccountReadDto = playerAccountService.createPlayerAccount(savedPlayer);
        return playerMapper.playerToDto(savedPlayer);
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public Player findByCredentials(String username, String password){
        return playerRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new PlayerException("Пользователь не найден"));
    }

    @Transactional
    public PlayerReadDto updatePlayer(PlayerCreateDto playerCreateDto){
        Player updatedPlayer = playerRepository.save(playerMapper.dtoToPlayer(playerCreateDto));
        PlayerAccount playerAccount = updatedPlayer.getPlayerAccount();
        PlayerAccountReadDto playerAccountReadDto = PlayerAccountReadDto.builder()
                .accountNumber(playerAccount.getAccountNumber())
                .balance(playerAccount.getBalance())
                .build();
        return playerMapper.playerToDto(updatedPlayer);
    }

    @Transactional
    public void deletePlayerById(int id){
        playerRepository.deleteById(id);
    }
}
