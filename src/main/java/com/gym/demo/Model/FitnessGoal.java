package com.gym.demo.Model;

import jakarta.persistence.*;

@Entity
public class FitnessGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String goalName;
    private String target; // Example: "Lose 5kg", "Run 10km"
    
    // Constructors
    public FitnessGoal() {}

    public FitnessGoal(User user, String goalName, String target) {
        this.user = user;
        this.goalName = goalName;
        this.target = target;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
