package com.gym.demo.Repository;

import com.gym.demo.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.time.LocalDate;


public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByLogDate(LocalDate logDate);
     @Query("SELECT a FROM ActivityLog a WHERE a.activityType LIKE :prefix%")
    List<ActivityLog> findByActivityTypeStartingWith(@Param("prefix") String prefix);
}