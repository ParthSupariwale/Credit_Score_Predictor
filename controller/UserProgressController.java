package com.fintech.creditscoring.controller;

import com.fintech.creditscoring.model.*;
import com.fintech.creditscoring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
public class UserProgressController {
    @Autowired
    private UserProgressService userProgressService;

    @PostMapping
    public UserProgress updateProgress(@RequestBody UserProgress progress) {
        return userProgressService.updateProgress(progress);
    }

    @GetMapping("/{userId}/{missionId}")
    public UserProgress getProgress(@PathVariable Long userId, @PathVariable Long missionId) {
        return userProgressService.getProgress(userId, missionId);
    }
}