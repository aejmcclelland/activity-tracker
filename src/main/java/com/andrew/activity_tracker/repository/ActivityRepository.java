package com.andrew.activity_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrew.activity_tracker.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
