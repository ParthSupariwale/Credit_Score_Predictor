package com.fintech.creditscoring.controller;

import com.fintech.creditscoring.model.*;
import com.fintech.creditscoring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit")
public class CreditScoreController {

    @Autowired
    private CreditScoreService creditScoreService;

    @GetMapping("/score/{userId}")
    public CreditScore getCreditScore(@PathVariable Long userId) {
        return creditScoreService.getCreditScore(userId);
    }

    @PostMapping("/score")
    public CreditScore saveCreditScore(@RequestBody CreditScore creditScore) {
        return creditScoreService.saveCreditScore(creditScore);
    }
}