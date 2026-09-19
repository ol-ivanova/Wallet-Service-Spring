package com.example.demo.service;

import com.example.demo.exception.AuthException;
import com.example.demo.model.domain.Player;
import com.example.demo.restclient.dto.JwtRequestDto;
import com.example.demo.restclient.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
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

        return new JwtResponseDto(jwt);
    }
}
