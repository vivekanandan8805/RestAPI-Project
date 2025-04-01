package com.gym.demo.Repository;

import com.gym.demo.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessGoalRepository extends JpaRepository<FitnessGoal, Long> {
}
