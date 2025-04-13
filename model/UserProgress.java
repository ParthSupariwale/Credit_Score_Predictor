package com.fintech.creditscoring.model;

import jakarta.persistence.*;

@Entity
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long missionId;
    private boolean completed;
    private int progress;

    public UserProgress() {}

    public UserProgress(Long userId, Long missionId) {
        this.userId = userId;
        this.missionId = missionId;
        this.completed = false;
        this.progress = 0;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {

    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMissionId() {
        return missionId;
    }
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getProgress() {
        return progress;
    }
    public void setProgress(int progress) {
        this.progress = progress;
    }

}
