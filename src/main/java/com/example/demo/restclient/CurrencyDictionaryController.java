package com.example.demo.restclient;

import com.example.demo.restclient.dto.DailyValute;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/valutes")
@RequiredArgsConstructor
public class CurrencyDictionaryController {
    private final CurrencyDictionaryService currencyDictionaryService;

    @GetMapping
    public ResponseEntity<DailyValute> getDailyValutes() {
        return ResponseEntity.ok(currencyDictionaryService.getDailyValutes());
    }
}
