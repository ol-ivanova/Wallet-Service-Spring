package com.example.demo.service;

import com.example.demo.mapper.PlayerAccountMapper;
import com.example.demo.model.domain.Player;
import com.example.demo.model.domain.PlayerAccount;
import com.example.demo.model.dto.*;
import com.example.demo.model.enums.AuditAction;
import com.example.demo.exception.PlayerAccountException;
import com.example.demo.exception.TransferException;
import com.example.demo.repository.PlayerAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerAccountService {
    private final PlayerAccountRepository playerAccountRepository;
    private final PlayerAccountMapper playerAccountMapper;
    private final PlayerAuditService playerAuditService;
    private final TransactionService transactionService;

    /**
     * Метод для создания аккаунта пользователя
     * @param player - сущность пользователя класса Player
     * @return - dto объект класса PlayerAccountReadDto
     */
    @Transactional
    public PlayerAccountReadDto createPlayerAccount(Player player){
        PlayerAccount playerAccount = PlayerAccount.builder()
                .balance(new BigDecimal(0))
                .player(player)
                .build();
        PlayerAccount savedPlayerAccount = playerAccountRepository.save(playerAccount);
        return playerAccountMapper.playerAccountToDto(savedPlayerAccount);
    }

    /**
     * Метод для создания аккаунта пользователя
     * @param playerAccountCreateDto - dto объект класса PlayerAccountCreateDto
     * @return - dto объект класса PlayerAccountReadDto
     */
    @Transactional
    public PlayerAccountReadDto createPlayerAccount(PlayerAccountCreateDto playerAccountCreateDto){
        PlayerAccount playerAccount = playerAccountMapper.dtoToPlayerAccount(playerAccountCreateDto);
        PlayerAccount savedPlayerAccount = playerAccountRepository.save(playerAccount);
        return playerAccountMapper.playerAccountToDto(savedPlayerAccount);
    }

    /**
     * Метод для поиска аккаунта пользователя по номеру счета
     * @param accountNumber - номер счета
     * @return - dto объект класса PlayerAccountReadDto
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PlayerAccountReadDto findPlayerAccountByAccountNumber(UUID accountNumber){
        if (TransactionSynchronizationManager.isActualTransactionActive()){
            TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
            log.info("Transaction: isNew = {}, name = {}", transactionStatus.isNewTransaction(), transactionStatus.getTransactionName());
        } else {
            log.info("There is no active transaction");
        }
        PlayerAccount playerAccount = playerAccountRepository.findById(accountNumber)
                .orElseThrow(() -> new PlayerAccountException("Аккаунт не найден"));
//        PlayerAuditCreateDto playerAuditCreateDto = PlayerAuditCreateDto.builder()
//                .player(playerAccount.getPlayer())
//                .action(AuditAction.LOGIN)
//                .build();
//        playerAuditService.createAudit(playerAuditCreateDto);
        PlayerAccountReadDto playerAccountReadDto = playerAccountMapper.playerAccountToDto(playerAccount);
        return playerAccountReadDto;

    }

    /**
     * Метод для обновления аккаунта пользователя
     * @param accountNumber - номер счета
     * @param playerAccountCreateDto - dto объект PlayerAccountCreateDto, содержащий новую информацию
     * @return - dto объект класса PlayerAccountReadDto
     */
    @Transactional
    public PlayerAccountReadDto updatePlayerAccount(UUID accountNumber, PlayerAccountCreateDto playerAccountCreateDto){
        PlayerAccount playerAccount = playerAccountRepository.findById(accountNumber)
                .orElseThrow(() -> new PlayerAccountException("Аккаунт не найден"));

        playerAccount.setBalance(playerAccountCreateDto.getBalance());

        return playerAccountMapper.playerAccountToDto(playerAccountRepository.save(playerAccount));
    }

    /**
     * Метод для удаления аккаунта пользователя по номеру счета
     * @param accountNumber - номер счета
     */
    @Transactional
    public void deletePlayerAccountByAccountNumber(UUID accountNumber){
        playerAccountRepository.deleteById(accountNumber);
    }

    /**
     * Метод для кредит операций
     * @param transactionCreateDto - dto объект класса TransactionCreateDto, содержащий информацию для транзакции
     */
    @Transactional
    public void doCreditOperation(TransactionCreateDto transactionCreateDto){
        PlayerAccount playerAccountTo = playerAccountRepository.findPlayerAccountByAccountNumber(
                transactionCreateDto.getPlayerAccountTo()
        );
        PlayerAccount playerAccountFrom = playerAccountRepository.findPlayerAccountByAccountNumber(
                transactionCreateDto.getPlayerAccountFrom()
        );
        if(transactionCreateDto.getSum().compareTo(playerAccountFrom.getBalance()) >0){
            throw new TransferException("Сумма не может превышать баланс");
        }
        playerAccountTo.setBalance(playerAccountTo.getBalance().add(transactionCreateDto.getSum()));
        playerAccountFrom.setBalance(playerAccountFrom.getBalance().subtract(transactionCreateDto.getSum()));

        transactionService.transferData(transactionCreateDto);
    }

    /**
     * Метод для дебит операций
     * @param transactionCreateDto - dto объект класса TransactionCreateDto, содержащий информацию для транзакции
     */
    @Transactional
    public void doDebitOperation(TransactionCreateDto transactionCreateDto){
        PlayerAccount playerAccountFrom = playerAccountRepository.findPlayerAccountByAccountNumber(
                transactionCreateDto.getPlayerAccountTo()
        );
        PlayerAccount playerAccountTo = playerAccountRepository.findPlayerAccountByAccountNumber(
                transactionCreateDto.getPlayerAccountFrom()
        );
        if(transactionCreateDto.getSum().compareTo(playerAccountTo.getBalance()) >0){
            throw new TransferException("Сумма не может превышать баланс");
        }
        playerAccountFrom.setBalance(playerAccountFrom.getBalance().subtract(transactionCreateDto.getSum()));
        playerAccountTo.setBalance(playerAccountTo.getBalance().add(transactionCreateDto.getSum()));

        transactionService.transferData(transactionCreateDto);
    }
}
