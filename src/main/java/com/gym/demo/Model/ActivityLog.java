package com.gym.demo.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activityType;
    private double value;
    private LocalDate logDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public ActivityLog() {}

    public ActivityLog(String activityType, double value, LocalDate logDate, User user) {
        this.activityType = activityType;
        this.value = value;
        this.logDate = logDate;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
