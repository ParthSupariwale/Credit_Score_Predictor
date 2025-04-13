package com.fintech.creditscoring.service;

import com.fintech.creditscoring.exception.ResourceNotFoundException;
import com.fintech.creditscoring.model.User;
import com.fintech.creditscoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Awards a badge to the user.
     *
     * @param userId The ID of the user.
     * @param badge  The badge to award.
     */
    public void awardBadge(Long userId, String badge) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        // Add the badge to the user's badges (assuming badges are stored as a list)
        user.getBadges().add(badge);

        // Save the updated user
        userRepository.save(user);
    }
}