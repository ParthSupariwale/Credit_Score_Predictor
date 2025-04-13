package com.fintech.creditscoring.controller;

import com.fintech.creditscoring.model.*;
import com.fintech.creditscoring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mission")
public class MissionController {
    @Autowired
    private MissionService missionService;

    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody Mission mission) {
        Mission createdMission = missionService.createMission(mission);
        return ResponseEntity.ok(createdMission);
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<Mission> getMission(@PathVariable Long missionId) {
        Mission mission = missionService.getMission(missionId);
        return ResponseEntity.ok(mission);
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completeMission(@RequestParam Long userId, @RequestParam Long missionId) {
        missionService.completeMission(userId, missionId);
        return ResponseEntity.ok("Mission completed successfully.");
    }

    @GetMapping("/progress")
    public ResponseEntity<UserProgress> getProgress(@RequestParam Long userId, @RequestParam Long missionId) {
        UserProgress progress = missionService.getProgress(userId, missionId);
        return ResponseEntity.ok(progress);
    }
}