package com.allo.bank.singleapi.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LatestIdrRatesFetcherTest {

    private WebClient webClient;
    private LatestIdrRatesFetcher fetcher;

    @BeforeEach
    void setUp() {
        webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);
        fetcher = new LatestIdrRatesFetcher(webClient);
        ReflectionTestUtils.setField(fetcher, "githubUsername", "muhamadnirwansyah");
    }

    @Test
    void shouldFetchAndCalculateSpreadCorrectly(){
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 0.000065);
        Map<String, Object> response = new HashMap<>();
        response.put("rates", rates);

        when(webClient.get()
                .uri(anyString())
                .retrieve()
                .bodyToMono(Map.class))
                .thenReturn(Mono.just(response));

        List<?> results = fetcher.fetchData();
        assertNotNull(results);
        assertEquals(1, results.size());
        Map<String,Object> data = (Map<String, Object>) results.get(0);
        assertTrue(data.containsKey("USD_BuySpread_IDR"));
    }
}