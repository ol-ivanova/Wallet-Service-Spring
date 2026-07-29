package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PlayerService playerService;

    /**
     * метод, загружающий пользователя по его логину
     *
     * @param username
     * @return UserDetails - одна из реализаций UserDetails
     * @throws UsernameNotFoundException - exception, если не удалось загнрузить пользователя
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return playerService.findPlayerByUsername(username)
                .map(player -> new User(
                        player.getUsername(),
                        player.getPassword(),
                        new ArrayList<>()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Не удалось загрузить пользователя: %s".formatted(username)));
    }
}
