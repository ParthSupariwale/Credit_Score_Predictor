package com.fintech.creditscoring.service;

import com.fintech.creditscoring.exception.ResourceNotFoundException;
import com.fintech.creditscoring.model.*;
import com.fintech.creditscoring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardService rewardService;

    public void completeMission(Long userId, Long missionId){
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new ResourceNotFoundException("Mission not found with ID: " + missionId));

        UserProgress progress = userProgressRepository.findByUserIdAndMissionId(userId, missionId).orElse(new UserProgress(userId, missionId));

        progress.setProgress(progress.getProgress() + 1);
        if(progress.getProgress() >= mission.getRequiredProgress()){
            progress.setCompleted(true);
            awardRewards(userId, mission.getRewardPoints(), mission.getBadge());
        }
        userProgressRepository.save(progress);
    }

    private void awardRewards(Long userId, int rewardPoints, String badge){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        user.setPoints(user.getPoints() + rewardPoints);

        if(badge != null && !badge.isEmpty()){
            rewardService.awardBadge(userId, badge);
        }

        userRepository.save(user);
    }

    public Mission createMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public Mission getMission(Long missionId) {
        return missionRepository.findById(missionId).orElseThrow(() -> new ResourceNotFoundException("Mission not found with ID: " + missionId));
    }

    public UserProgress getProgress(Long userId, Long missionId) {
        return userProgressRepository.findByUserIdAndMissionId(userId, missionId).orElseThrow(() -> new ResourceNotFoundException("Progress not found foruser ID:" + userId + " and mission ID:" + missionId));
    }
}
