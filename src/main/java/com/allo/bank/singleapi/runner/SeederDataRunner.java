package com.allo.bank.singleapi.runner;

import com.allo.bank.singleapi.store.FinanceDataStore;
import com.allo.bank.singleapi.strategy.IDRDataFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeederDataRunner implements ApplicationRunner {

    private final List<IDRDataFetcher> fetchers;
    private final FinanceDataStore financeDataStore;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Seeder data runner on process..");
        Map<String, List<?>> aggregated = new HashMap<>();
        for (IDRDataFetcher dataFetcher : fetchers){
            aggregated.put(dataFetcher.getResourceType(), dataFetcher.fetchData());
        }
        financeDataStore.initialize(aggregated);
    }
}
