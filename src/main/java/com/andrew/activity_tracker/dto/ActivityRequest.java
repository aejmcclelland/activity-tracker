package com.andrew.activity_tracker.dto;

import java.time.LocalDate;

import com.andrew.activity_tracker.model.ActivityType;

public class ActivityRequest {

private ActivityType activityType;
public ActivityType getActivityType() {
    return activityType;
}
 public void setActivityType(ActivityType activityType) {
    this.activityType = activityType;
 }
private LocalDate activityDate;
public LocalDate getActivityDate() {
    return activityDate;
}
public void setActivityDate(LocalDate activityDate) {
    this.activityDate = activityDate;
}
private Integer durationMinutes;
public Integer getDurationMinutes() {
    return durationMinutes;
}
public void setDurationMinutes(Integer durationMinutes) {
    this.durationMinutes = durationMinutes;
}
private Double distanceMiles;
public Double getDistanceMiles() {
    return distanceMiles;
}
public void setDistanceMiles(Double distanceMiles) {
    this.distanceMiles = distanceMiles;
}
private String notes;
public String getNotes() {
    return notes;
}
public void setNotes(String notes) {
    this.notes = notes;
}


}
