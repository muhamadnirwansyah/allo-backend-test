package com.allo.bank.singleapi.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoricalIdrUsdFetcher implements IDRDataFetcher{

    private final WebClient webClient;

    @Override
    public String getResourceType() {
        return "historical_idr_usd";
    }

    @Override
    public List<?> fetchData() {
        Map response = webClient.get()
                .uri("/2024-01-01..2024-01-05?from=IDR&to=USD")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        return List.of(response);
    }
}
