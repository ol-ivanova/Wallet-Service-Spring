package com.example.demo.restclient;

import com.example.demo.restclient.dto.DailyValute;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyDictionaryService {
    private final CurrencyDictionaryRestClient currencyDictionaryRestClient;
    private final ObjectMapper objectMapper;

    public DailyValute getDailyValutes() {
        DailyValute dailyValute = currencyDictionaryRestClient.getDailyValute();
        log.info("valute: {}", dailyValute);

        return dailyValute;
    }
}
