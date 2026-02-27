package com.allo.bank.singleapi.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SupportedCurrenciesFetcherTest {

    private WebClient webClient;
    private SupportedCurrenciesFetcher fetcher;

    @BeforeEach
    void setUp() {
        webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);
        fetcher = new SupportedCurrenciesFetcher(webClient);
    }

    @Test
    void shouldReturnSupportedCurrencies() {
        Map<String, Object> mockResponse = Map.of("IDR", "Indonesian Rupiah");
        when(webClient.get().uri(anyString())
                .retrieve().bodyToMono(Map.class))
                .thenReturn(Mono.just(mockResponse));

        List<?> results = fetcher.fetchData();
        assertNotNull(results);
        assertEquals(1, results.size());
    }
}