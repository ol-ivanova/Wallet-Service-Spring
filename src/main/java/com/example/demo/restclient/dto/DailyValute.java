package com.example.demo.restclient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.util.StdConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyValute {
    @JsonProperty("Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime date;

    @JsonProperty("PreviousDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime previousDate;

    @JsonProperty("PreviousURL")
    private String previousUrl;

    @JsonProperty("Timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime timestamp;

    @JsonProperty("Valute")
    private Map<String, Valute> valutes;

    @Data
    public static class Valute {
        @JsonProperty("ID")
        private String id;

        @JsonProperty("NumCode")
        private String numCode;

        @JsonProperty("CharCode")
        private String charCode;

        @JsonProperty("Nominal")
        private int nominal;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Value")
        @JsonDeserialize(converter = StringToBigDecimalConverter.class)
        private BigDecimal value;

        @JsonProperty("Previous")
        @JsonDeserialize(converter = StringToBigDecimalConverter.class)
        private BigDecimal previous;

        private static class StringToBigDecimalConverter extends StdConverter<String, BigDecimal> {
            @Override
            public BigDecimal convert(String value) {
                if (value == null || value.isEmpty()) {
                    return null;
                }
                return new BigDecimal(value.replace(',', '.'));
            }
        }
    }
}