package com.andrew.activity_tracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.andrew.activity_tracker.model.Activity;
import com.andrew.activity_tracker.model.ActivityType;

public class ActivityResponse {

    private Long id;

    private ActivityType activityType;

    private LocalDate activityDate;

    private Integer durationMinutes;

    private Double distanceMiles;

    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ActivityResponse from(Activity activity) {

        ActivityResponse response = new ActivityResponse();

        response.id = activity.getId();

        response.activityType = activity.getActivityType();

        response.activityDate = activity.getActivityDate();

        response.durationMinutes = activity.getDurationMinutes();

        response.distanceMiles = activity.getDistanceMiles();

        response.notes = activity.getNotes();

        response.createdAt = activity.getCreatedAt();

        response.updatedAt = activity.getUpdatedAt();

        return response;

    }

    public Long getId() {
        return id;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public Double getDistanceMiles() {
        return distanceMiles;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
