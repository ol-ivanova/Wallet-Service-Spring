package com.example.demo.controller;

import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.model.dto.PlayerProjectionByClass;
import com.example.demo.model.dto.PlayerProjectionByInterface;
import com.example.demo.model.dto.PlayerReadDto;
import com.example.demo.model.params.PageableParams;
import com.example.demo.model.params.PageableResult;
import com.example.demo.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @RestController = @Controller + @ResponseBody
 */
@RestController
@RequestMapping(value = "/api/v1/players", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name ="Пользователь", description = "Операции управления пользователями")
public class PlayerController {
    private final PlayerService playerService;

    @Operation(summary = "Создание пользователя")
    @PostMapping
//    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<PlayerReadDto> createPlayer(
            @RequestBody PlayerCreateDto playerCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(playerCreateDto));
    }

    @GetMapping("/all")
    public PageableResult<List<PlayerReadDto>> findAll(PageableParams params){
        Page<PlayerReadDto> allPlayers = playerService.findAllPage(params);
        return new PageableResult<>(
                allPlayers.getContent(),
                params.getOffset(),
                params.getLimit(),
                allPlayers.getTotalElements()
        );
    }

    @GetMapping("/interface")
    public ResponseEntity<List<PlayerProjectionByInterface>> findAllByInterface(){
        return ResponseEntity.ok(playerService.findAllByInterface());
    }

    @GetMapping("/class")
    public ResponseEntity<List<PlayerProjectionByClass>> findAllByClass(){
        return ResponseEntity.ok(playerService.findAllByClass());
    }

    @Operation(summary = "Авторизация пользователя")
    @GetMapping
    public ResponseEntity<PlayerReadDto> authorizeUser(
            @Parameter(description = "Логин")
            @RequestParam String username,
            @Parameter(description = "Пароль")
            @RequestParam String password){
        return ResponseEntity.ok(playerService.findByCredentials(username, password));
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @Parameter(description = "ID пользователя")
            @PathVariable Integer id){
        playerService.deletePlayerById(id);
    }
}
