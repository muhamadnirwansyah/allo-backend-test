package com.allo.bank.singleapi.strategy;

import java.util.List;

public interface IDRDataFetcher {
    String getResourceType();
    List<?> fetchData();
}
