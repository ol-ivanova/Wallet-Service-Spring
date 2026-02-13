package com.example.demo.service;

import com.example.demo.mapper.PlayerAccountMapper;
import com.example.demo.model.domain.Player;
import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.dto.PlayerAccountCreateDto;
import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.model.dto.TransactionCreateDto;
import com.example.demo.model.exception.PlayerAccountException;
import com.example.demo.model.exception.TransferException;
import com.example.demo.repository.PlayerAccountRepository;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerAccountService {
    private final PlayerAccountRepository playerAccountRepository;
    private final PlayerAccountMapper playerAccountMapper;

    @Transactional
    public PlayerAccountReadDto createPlayerAccount(Player player){
        PlayerAccount playerAccount = PlayerAccount.builder()
                .balance(new BigDecimal(0))
                .player(player)
                .build();
        PlayerAccount savedPlayerAccount = playerAccountRepository.save(playerAccount);
        return playerAccountMapper.playerAccountToDto(savedPlayerAccount);
    }

    public List<PlayerAccount> findAll(){
        return playerAccountRepository.findAll();
    }

    public PlayerAccount findPlayerAccountById(UUID id){
        Optional<PlayerAccount> byId = playerAccountRepository.findById(id);
//                .orElseThrow(() -> new PlayerAccountException("Аккаунт не найден"));
        PlayerAccount playerAccount = byId.get();
        Player player = playerAccount.getPlayer();
        Integer idPlayer = player.getId();
        String name = player.getName();
        return playerAccount;

    }

    @Transactional
    public PlayerAccountReadDto updatePlayerAccount(PlayerAccountCreateDto playerAccountCreateDto){
        PlayerAccount updatedPlayerAccount = playerAccountRepository.save(
                playerAccountMapper.dtoToPlayerAccount(playerAccountCreateDto)
        );
        return playerAccountMapper.playerAccountToDto(updatedPlayerAccount);
    }

    @Transactional
    public void deletePlayerAccountById(UUID id){
        playerAccountRepository.deleteById(id);
    }

    @Transactional
    public void doCreditOperation(TransactionCreateDto transactionCreateDto){
        PlayerAccount playerAccountTo = playerAccountRepository.findPlayerAccountByAccountNumber(
                transactionCreateDto.getPlayerAccountTo()
        );
        PlayerAccount playerAccountFrom = playerAccountRepository.findPlayerAccountByAccountNumber(
                transactionCreateDto.getPlayerAccountFrom()
        );
        if(transactionCreateDto.getSum().compareTo(playerAccountFrom.getBalance()) >=0){
            throw new TransferException("Сумма не может превышать баланс");
        }

        playerAccountTo.setBalance(playerAccountTo.getBalance().add(transactionCreateDto.getSum()));
        playerAccountFrom.setBalance(playerAccountFrom.getBalance().subtract(transactionCreateDto.getSum()));
    }

    @Transactional
    public void doDebitOperation(TransactionCreateDto transactionCreateDto){
        PlayerAccount playerAccountFrom = playerAccountRepository.findPlayerAccountByAccountNumber(
                transactionCreateDto.getPlayerAccountTo()
        );
        PlayerAccount playerAccountTo = playerAccountRepository.findPlayerAccountByAccountNumber(
                transactionCreateDto.getPlayerAccountFrom()
        );
        if(transactionCreateDto.getSum().compareTo(playerAccountTo.getBalance()) >=0){
            throw new TransferException("Сумма не может превышать баланс");
        }
        playerAccountFrom.setBalance(playerAccountFrom.getBalance().subtract(transactionCreateDto.getSum()));
        playerAccountTo.setBalance(playerAccountTo.getBalance().add(transactionCreateDto.getSum()));
    }
}
