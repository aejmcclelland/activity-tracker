package com.andrew.activity_tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrew.activity_tracker.dto.ActivityRequest;
import com.andrew.activity_tracker.dto.ActivityResponse;
import com.andrew.activity_tracker.model.Activity;
import com.andrew.activity_tracker.service.ActivityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@Valid @RequestBody ActivityRequest request) {
        Activity activity = toActivity(request);
        Activity savedActivity = activityService.createActivity(activity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ActivityResponse.from(savedActivity));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getAllActivities() {
        List<ActivityResponse> activities = activityService.getAllActivities().stream()
                .map(ActivityResponse::from)
                .toList();

        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable("id") Long id) {
        return activityService.getActivityById(id)
                .map(activity -> ResponseEntity.ok(ActivityResponse.from(activity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponse> updateActivity(
            @PathVariable("id") Long id,
            @Valid @RequestBody ActivityRequest request) {
        Activity updatedActivity = toActivity(request);

        return activityService.updateActivity(id, updatedActivity)
                .map(activity -> ResponseEntity.ok(ActivityResponse.from(activity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable("id") Long id) {
        boolean deleted = activityService.deleteActivity(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    private Activity toActivity(ActivityRequest request) {
        Activity activity = new Activity();
        activity.setActivityType(request.getActivityType());
        activity.setActivityDate(request.getActivityDate());
        activity.setDurationMinutes(request.getDurationMinutes());
        activity.setDistanceMiles(request.getDistanceMiles());
        activity.setNotes(request.getNotes());
        return activity;
    }
}
