package com.andrew.activity_tracker.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import com.andrew.activity_tracker.model.ActivityType;

public class ActivityRequest {

    @NotNull(message = "Activity type is required")
    private ActivityType activityType;

    @NotNull(message = "Activity date is required")
    private LocalDate activityDate;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer durationMinutes;

    @PositiveOrZero(message = "Distance cannot be negative")
    private Double distanceMiles;

    @Size(max = 500, message = "Notes cannot be longer than 500 characters")
    private String notes;

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Double getDistanceMiles() {
        return distanceMiles;
    }

    public void setDistanceMiles(Double distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
