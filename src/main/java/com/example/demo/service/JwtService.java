package com.example.demo.service;

import com.example.demo.model.domain.Player;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final SecretKey jwtSecretKey;
    private final Duration jwtLifetime;

    public JwtService(@Value("${jwt.secret}") String jwtSecretKey, @Value("${jwt.lifetime}") Duration jwtLifetime) {
        this.jwtSecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
        this.jwtLifetime = jwtLifetime;
    }

    /**
     * Метод для генерации токена
     * @param player - данные пользователя
     * @return - готовый JWT-токен в виде строки
     */
    public String generateToken(Player player) {
        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());

        return Jwts.builder()
                .claim("playerId", player.getId())
                .issuer(player.getName())
                .subject(player.getUsername())
                .issuedAt(issuedDate)
                .expiration(expirationDate)
                .signWith(jwtSecretKey)
                .compact();
    }

    /**
     * Метод, извлекающий логин из токен
     * @param jwt
     * @return
     */
    public String getLogin(String jwt) {
        return getClaim(jwt, Claims::getSubject);
    }

    /**
     * generic-метод, извлекающий заданную в claimResolver информацию из токена
     * @param jwt - jwt токен
     * @param claimsResolver - лямбда-выражение
     * @return T - информация, извлеченная в соответствии с лямбда-выражеинием
     * @param <T> - generic-метод
     */
    public <T> T getClaim(String jwt, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    /**
     * Метод, извлекающий все claims (заявки) из токена
     */
    private Claims getAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(jwtSecretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
