package com.fintech.creditscoring.service;

import com.fintech.creditscoring.exception.ResourceNotFoundException;
import com.fintech.creditscoring.model.*;
import com.fintech.creditscoring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserProgressService {
    @Autowired
    private UserProgressRepository userProgressRepository;

    public UserProgress updateProgress(UserProgress progress) {
        return userProgressRepository.save(progress);
    }

    public UserProgress getProgress(Long userId, Long missionId) {
        return userProgressRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found for user ID: " + userId + " and mission ID: " + missionId));
    }
}
