package com.allo.bank.singleapi.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinanceDataStore {

    private final ObjectMapper objectMapper;
    private Map<String, List<?>> data = Map.of();

    public synchronized void initialize(Map<String, List<?>> inputData){
        doLogging(inputData);
        this.data = Map.copyOf(inputData);
    }

    private void doLogging(Object object){
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        log.info("initialize process input data : {}",json);
    }

    public List<?> getData(String resourceType){
        return data.get(resourceType);
    }
}
