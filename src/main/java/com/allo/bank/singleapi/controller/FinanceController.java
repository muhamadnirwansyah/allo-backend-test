package com.allo.bank.singleapi.controller;

import com.allo.bank.singleapi.store.FinanceDataStore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/finance/data")
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceDataStore financeDataStore;

    @GetMapping("/{resourceType}")
    public ResponseEntity<?> getData(@PathVariable(value = "resourceType") String resourceType) {
        List<?> data = financeDataStore.getData(resourceType);
        if (Objects.isNull(data)) {
            return ResponseEntity.badRequest().body("Invalid resource type");
        }
        return ResponseEntity.ok(data);
    }
}
