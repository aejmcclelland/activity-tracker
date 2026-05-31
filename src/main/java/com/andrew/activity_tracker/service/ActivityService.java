package com.andrew.activity_tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andrew.activity_tracker.model.Activity;
import com.andrew.activity_tracker.repository.ActivityRepository;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public Optional<Activity> updateActivity(Long id, Activity updatedActivity) {
        return activityRepository.findById(id)
                .map(existingActivity -> {
                    existingActivity.setActivityType(updatedActivity.getActivityType());
                    existingActivity.setActivityDate(updatedActivity.getActivityDate());
                    existingActivity.setDurationMinutes(updatedActivity.getDurationMinutes());
                    existingActivity.setDistanceMiles(updatedActivity.getDistanceMiles());
                    existingActivity.setNotes(updatedActivity.getNotes());

                    return activityRepository.save(existingActivity);
                });
    }

    public boolean deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            return false;
        }

        activityRepository.deleteById(id);
        return true;
    }
}
