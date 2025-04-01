package com.gym.demo.Service;

import com.gym.demo.Model.ActivityLog;
import com.gym.demo.Repository.ActivityLogRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityLogService {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    public List<ActivityLog> getAllActivityLogs() {
        return activityLogRepository.findAll();
    }

    public Optional<ActivityLog> getActivityLogById(Long id) {
        return activityLogRepository.findById(id);
    }

    public ActivityLog saveActivityLog(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }

    public ActivityLog updateActivityLog(Long id, ActivityLog updatedActivityLog) {
        return activityLogRepository.findById(id).map(existingLog -> {
            existingLog.setActivityType(updatedActivityLog.getActivityType());
            existingLog.setValue(updatedActivityLog.getValue());
            existingLog.setLogDate(updatedActivityLog.getLogDate());
            existingLog.setUser(updatedActivityLog.getUser());
            return activityLogRepository.save(existingLog);
        }).orElseThrow(() -> new RuntimeException("ActivityLog not found with id " + id));
    }

    public void deleteActivityLog(Long id) {
        activityLogRepository.deleteById(id);
    }
    public List<ActivityLog> getActivityLogsByLogDate(LocalDate logDate) {
        return activityLogRepository.findByLogDate(logDate);
    }
    public List<ActivityLog> getActivityLogsByActivityTypePrefix(String prefix) {
        return activityLogRepository.findByActivityTypeStartingWith(prefix);
    }
}
