package com.example.demo.controller;

import com.example.demo.model.dto.PlayerAccountReadDto;
import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerReadDto;
import com.example.demo.service.PlayerService;
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
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    public PlayerReadDto createPlayer(@RequestBody PlayerCreateDto playerCreateDto){
        return playerService.createPlayer(playerCreateDto);
    }

    @GetMapping
    public PlayerReadDto authorizeUser(@RequestParam String username,
                                       @RequestParam String password){
        return playerService.findByCredentials(username, password);
    }

    /**
     * PUT метод - предполагает обновление сразу всех полей, неуказанные поля будут null,
     * т.е. нам приходится указывать все поля, даже если хотим изменить какое-то одно
     */
    @PutMapping("/{id}")
    public PlayerReadDto updateUser(@PathVariable Integer id,
                                    @RequestBody PlayerCreateDto playerCreateDto){
        return playerService.updatePlayer(id, playerCreateDto);
    }

    @PatchMapping("/{id}")
    public PlayerReadDto updateUserPatch(@PathVariable Integer id,
                                    @RequestBody PlayerCreateDto playerCreateDto){
        return playerService.updatePlayer(id, playerCreateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        playerService.deletePlayerById(id);
    }
}
