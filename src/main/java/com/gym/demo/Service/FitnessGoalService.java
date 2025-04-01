package com.gym.demo.Service;

import com.gym.demo.Model.FitnessGoal;
import com.gym.demo.Repository.FitnessGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FitnessGoalService {
    @Autowired
    private FitnessGoalRepository fitnessGoalRepository;

    public List<FitnessGoal> getAllFitnessGoals() {
        return fitnessGoalRepository.findAll();
    }

    public Optional<FitnessGoal> getFitnessGoalById(Long id) {
        return fitnessGoalRepository.findById(id);
    }

    public FitnessGoal saveFitnessGoal(FitnessGoal fitnessGoal) {
        return fitnessGoalRepository.save(fitnessGoal);
    }

    public FitnessGoal updateFitnessGoal(Long id, FitnessGoal updatedGoal) {
        Optional<FitnessGoal> optionalFitnessGoal = fitnessGoalRepository.findById(id);
        if (optionalFitnessGoal.isPresent()) {
            FitnessGoal existingGoal = optionalFitnessGoal.get();
            existingGoal.setGoalName(updatedGoal.getGoalName());
            existingGoal.setTarget(updatedGoal.getTarget());
            return fitnessGoalRepository.save(existingGoal);
        } else {
            throw new RuntimeException("FitnessGoal not found with id " + id);
        }
    }

    public void deleteFitnessGoal(Long id) {
        if (fitnessGoalRepository.existsById(id)) {
            fitnessGoalRepository.deleteById(id);
        } else {
            throw new RuntimeException("FitnessGoal not found with id " + id);
        }
    }
}
