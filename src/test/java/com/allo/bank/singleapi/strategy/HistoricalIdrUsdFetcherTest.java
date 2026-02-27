package com.allo.bank.singleapi.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class HistoricalIdrUsdFetcherTest {

    @Test
    void shouldReturnHistoricalData(){
        WebClient webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);
        HistoricalIdrUsdFetcher fetcher = new HistoricalIdrUsdFetcher(webClient);
        Map<String, Object> mockResponse = Map.of("rates", Map.of());
        when(webClient.get().uri(anyString()).retrieve()
                .bodyToMono(Map.class)).thenReturn(Mono.just(mockResponse));
        List<?> results = fetcher.fetchData();
        assertNotNull(results);
        assertEquals(1, results.size());
    }

}