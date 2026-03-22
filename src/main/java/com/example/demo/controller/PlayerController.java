package com.example.demo.controller;

import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerReadDto;
import com.example.demo.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController = @Controller + @ResponseBody
 */
@RestController
@RequestMapping("/api/v1/player")
@RequiredArgsConstructor
@Tag(name ="Пользователь", description = "Операции управления пользователями")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/test")
    public void test(){
        playerService.test();
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping
    public PlayerReadDto createPlayer(
            @RequestBody PlayerCreateDto playerCreateDto){
        return playerService.createPlayer(playerCreateDto);
    }

    @Operation(summary = "Авторизация пользователя")
    @GetMapping
    public PlayerReadDto authorizeUser(
            @Parameter(description = "Логин")
            @RequestParam String username,
            @Parameter(description = "Пароль")
            @RequestParam String password){
        return playerService.findByCredentials(username, password);
    }

    /**
     * PUT метод - предполагает обновление сразу всех полей, неуказанные поля будут null,
     * т.е. нам приходится указывать все поля, даже если хотим изменить какое-то одно
     */
    @Operation(summary = "Обновление пользователя")
    @PutMapping("/{id}")
    public PlayerReadDto updateUser(
            @Parameter(description = "ID пользователя")
            @PathVariable Integer id,
            @RequestBody PlayerCreateDto playerCreateDto){
        return playerService.updatePlayer(id, playerCreateDto);
    }

    @Operation(summary = "Частичное обновление пользователя")
    @PatchMapping("/{id}")
    public PlayerReadDto updateUserPatch(
            @Parameter(description = "ID пользователя")
            @PathVariable Integer id,
            @RequestBody PlayerCreateDto playerCreateDto){
        return playerService.updatePlayer(id, playerCreateDto);
    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/{id}")
    public void deleteUser(
            @Parameter(description = "ID пользователя")
            @PathVariable Integer id){
        playerService.deletePlayerById(id);
    }
}
