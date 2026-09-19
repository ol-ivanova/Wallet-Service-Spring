package com.example.demo.service;

import com.example.demo.exception.AuthException;
import com.example.demo.model.domain.Player;
import com.example.demo.model.dto.JwtRequestDto;
import com.example.demo.model.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PlayerService playerService;
    private final JwtService jwtService;

    /**
     * Метод аутентификации пользователя
     * @param requestDto - dto с данными пользователя
     * @return - jwt токен
     */
    public JwtResponseDto authenticate(JwtRequestDto requestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getLogin(),
                            requestDto.getPassword()
                    )
            );
        } catch (AuthenticationException e){
            throw new AuthException("Неверный логин или пароль");
        }

        Player player = playerService.findPlayerByUsername(requestDto.getLogin()).orElse(null);
        String jwt = jwtService.generateToken(player);

        log.debug("JWT token: {}", jwt);
        return new JwtResponseDto(jwt);
    }
}
