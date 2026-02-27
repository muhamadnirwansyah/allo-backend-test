package com.allo.bank.singleapi.client;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public class FrankfurterClientFactoryBean implements FactoryBean<WebClient> {

    @Value("${app.frankfurter.base-url}")
    private String baseUrl;

    @Override
    public @Nullable WebClient getObject() throws Exception {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public @Nullable Class<?> getObjectType() {
        return WebClient.class;
    }
}
