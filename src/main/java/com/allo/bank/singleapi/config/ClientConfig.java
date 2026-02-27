package com.allo.bank.singleapi.config;

import com.allo.bank.singleapi.client.FrankfurterClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public FrankfurterClientFactoryBean frankfurterClientFactoryBean(){
        return new FrankfurterClientFactoryBean();
    }
}
