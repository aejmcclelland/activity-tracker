package com.andrew.activity_tracker.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.andrew.activity_tracker.model.Activity;
import com.andrew.activity_tracker.model.ActivityType;
import com.andrew.activity_tracker.repository.ActivityRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ActivityControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActivityRepository activityRepository;

    @BeforeEach
    void setUp() {
        activityRepository.deleteAll();
    }

    @Test
    void createActivityReturnsCreatedActivity() throws Exception {
        String requestBody = """
                {
                    "activityType": "RUNNING",
                    "activityDate": "2026-06-01",
                    "durationMinutes": 30,
                    "distanceMiles": 3.2,
                    "notes": "Easy run"
                }
                """;

        mockMvc.perform(post("/api/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.activityType").value("RUNNING"))
                .andExpect(jsonPath("$.durationMinutes").value(30))
                .andExpect(jsonPath("$.notes").value("Easy run"));
    }

    @Test
    void getAllActivitiesReturnsActivities() throws Exception {
        saveActivity(ActivityType.RUNNING, LocalDate.of(2026, 6, 1), 30, 3.2, "Easy run");
        saveActivity(ActivityType.CYCLING, LocalDate.of(2026, 6, 2), 45, 12.5, "Evening cycle");

        mockMvc.perform(get("/api/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].activityType").value("RUNNING"))
                .andExpect(jsonPath("$[1].activityType").value("CYCLING"));
    }

    @Test
    void getActivityByIdReturnsNotFoundWhenMissing() throws Exception {
        mockMvc.perform(get("/api/activities/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void createActivityReturnsBadRequestForInvalidRequest() throws Exception {
        String requestBody = """
                {
                    "activityType": "RUNNING",
                    "activityDate": "2026-06-01",
                    "durationMinutes": 0,
                    "distanceMiles": -1,
                    "notes": "Invalid activity"
                }
                """;

        mockMvc.perform(post("/api/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    private Activity saveActivity(
            ActivityType activityType,
            LocalDate activityDate,
            Integer durationMinutes,
            Double distanceMiles,
            String notes) {
        Activity activity = new Activity();
        activity.setActivityType(activityType);
        activity.setActivityDate(activityDate);
        activity.setDurationMinutes(durationMinutes);
        activity.setDistanceMiles(distanceMiles);
        activity.setNotes(notes);
        return activityRepository.save(activity);
    }
}
