package com.allo.bank.singleapi.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LatestIdrRatesFetcher implements IDRDataFetcher{

    private final WebClient webClient;
    @Value("${github.username}")
    private String githubUsername;

    @Override
    public String getResourceType() {
        return "latest_idr_rates";
    }

    @Override
    public List<?> fetchData() {
        Map response = webClient.get()
                .uri("/latest?base=IDR")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        Map<String, Double> rates = (Map<String, Double>) response.get("rates");
        double usdRate = rates.get("USD");
        double spreadFactor = calculateSpread(githubUsername);
        double usdBuySpread = (1/usdRate) * (1 + spreadFactor);

        Map<String, Object> result = new HashMap<>(rates);
        result.put("USD_BuySpread_IDR", usdBuySpread);
        return List.of(result);
    }

    private double calculateSpread(String username){
        int sum = username.toLowerCase()
                .chars()
                .sum();
        return (sum % 1000) / 100000.0;
    }
}
