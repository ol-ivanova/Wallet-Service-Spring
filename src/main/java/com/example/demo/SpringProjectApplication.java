package com.example.demo;

import com.example.demo.model.dto.PlayerCreateDto;
import com.example.demo.service.PlayerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringProjectApplication.class, args);
        PlayerService playerService = context.getBean(PlayerService.class);
        playerService.firstFind();
    }
}
