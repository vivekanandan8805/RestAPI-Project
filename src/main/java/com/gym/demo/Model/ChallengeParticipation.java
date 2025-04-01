package com.gym.demo.Model;

import jakarta.persistence.*;

@Entity
public class ChallengeParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String challengeName;
    private String status;  // Example: "Completed", "In Progress"
    
    // Constructors
    public ChallengeParticipation() {}

    public ChallengeParticipation(User user, String challengeName, String status) {
        this.user = user;
        this.challengeName = challengeName;
        this.status = status;
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

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
