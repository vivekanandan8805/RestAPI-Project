package com.gym.demo.Controller;

import com.gym.demo.Model.ActivityLog;
import com.gym.demo.Service.ActivityLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activity-logs")
@Tag(name = "Activity Log API", description = "APIs for managing user activity logs")
public class ActivityLogController {

    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping
    @Operation(summary = "Get all activity logs", description = "Fetch a list of all activity logs")
    public List<ActivityLog> getAllActivityLogs() {
        return activityLogService.getAllActivityLogs();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get activity log by ID", description = "Fetch a specific activity log using its ID")
    public ResponseEntity<ActivityLog> getActivityLogById(@PathVariable Long id) {
        Optional<ActivityLog> activityLog = activityLogService.getActivityLogById(id);
        return activityLog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new activity log", description = "Add a new activity log entry")
    public ActivityLog createActivityLog(@RequestBody ActivityLog activityLog) {
        return activityLogService.saveActivityLog(activityLog);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an activity log", description = "Modify an existing activity log entry by ID")
    public ResponseEntity<ActivityLog> updateActivityLog(@PathVariable Long id, @RequestBody ActivityLog updatedActivityLog) {
        try {
            ActivityLog updated = activityLogService.updateActivityLog(id, updatedActivityLog);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an activity log", description = "Remove an activity log entry using its ID")
    public ResponseEntity<Void> deleteActivityLog(@PathVariable Long id) {
        activityLogService.deleteActivityLog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-date")
    @Operation(summary = "Get activity logs by date", description = "Fetch all activity logs for a specific log date")
    public ResponseEntity<List<ActivityLog>> getActivityLogsByLogDate(@RequestParam LocalDate logDate) {
        List<ActivityLog> logs = activityLogService.getActivityLogsByLogDate(logDate);
        return logs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(logs);
    }

    @GetMapping("/by-activity-type")
    @Operation(summary = "Get activity logs by activity type prefix", description = "Fetch all activity logs where activity type starts with the given prefix")
    public ResponseEntity<List<ActivityLog>> getActivityLogsByActivityTypePrefix(@RequestParam String prefix) {
        List<ActivityLog> logs = activityLogService.getActivityLogsByActivityTypePrefix(prefix);
        return logs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(logs);
    }
}
