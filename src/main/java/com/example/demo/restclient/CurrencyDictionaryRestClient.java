package com.example.demo.restclient;

import com.example.demo.restclient.dto.DailyValute;
import com.example.demo.restclient.dto.JavaScriptHttpMessageConverter;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.JacksonXmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyDictionaryRestClient {
    private RestClient restClient;
    @Value("${app.integration.currency-dictionary.client.url}")
    private String url;
    private final JavaScriptHttpMessageConverter javaScriptHttpMessageConverter;

    @PostConstruct
    private void init() {
        restClient = RestClient.builder()
                .configureMessageConverters(configurer -> {
                    // Добавляем наш конвертер в начало списка
                    configurer.addCustomConverter(javaScriptHttpMessageConverter);
                })
                .build();
    }

//    @Retryable(
//            retryFor = RuntimeException.class,
//            maxAttemptsExpression = "${app.sync.currency-dictionary.retry.maxAttempts:3}",
//            backoff = @Backoff(delayExpression = "${app.sync.currency-dictionary.retry.delay:300000}")
//    )
    public DailyValute getDailyValute(){
        DailyValute dailyValute;
        try {
            dailyValute = restClient.get()
                    .uri(url)
//                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(DailyValute.class)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка интеграции", e);
        }

        return dailyValute;
    }

//    @Recover
//    public DailyCurrency getDailyCurrencyRecover(IntegrationException e) throws Exception {
//        log.error("CurrencyDictionaryRestClient", e);
//        throw e;
//    }
}