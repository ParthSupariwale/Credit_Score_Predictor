package com.fintech.creditscoring.service;

import com.fintech.creditscoring.exception.ResourceNotFoundException;
import com.fintech.creditscoring.model.*;
import com.fintech.creditscoring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditScoreService {

    @Autowired
    private CreditScoreRepository creditScoreRepository;

    public CreditScore getCreditScore(Long userId) {
        return creditScoreRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Credit score not found for user ID: " + userId));
    }

    public CreditScore saveCreditScore(CreditScore creditScore) {
        return creditScoreRepository.save(creditScore);
    }
}