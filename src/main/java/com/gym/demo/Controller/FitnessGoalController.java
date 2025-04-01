package com.gym.demo.Controller;

import com.gym.demo.Model.FitnessGoal;
import com.gym.demo.Service.FitnessGoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fitness-goals")
@Tag(name = "Fitness Goal API", description = "APIs for managing user fitness goals")
public class FitnessGoalController {

    @Autowired
    private FitnessGoalService fitnessGoalService;

    @GetMapping
    @Operation(summary = "Get all fitness goals", description = "Retrieve a list of all fitness goals")
    public List<FitnessGoal> getAllFitnessGoals() {
        return fitnessGoalService.getAllFitnessGoals();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get fitness goal by ID", description = "Retrieve a specific fitness goal by its ID")
    public ResponseEntity<FitnessGoal> getFitnessGoalById(
        @PathVariable @Parameter(description = "ID of the fitness goal") Long id) {
        
        Optional<FitnessGoal> fitnessGoal = fitnessGoalService.getFitnessGoalById(id);
        return fitnessGoal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new fitness goal", description = "Add a new fitness goal entry")
    public FitnessGoal createFitnessGoal(
        @RequestBody @Parameter(description = "Fitness goal details") FitnessGoal fitnessGoal) {
        
        return fitnessGoalService.saveFitnessGoal(fitnessGoal);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a fitness goal", description = "Modify an existing fitness goal by ID")
    public ResponseEntity<FitnessGoal> updateFitnessGoal(
        @PathVariable @Parameter(description = "ID of the fitness goal") Long id, 
        @RequestBody @Parameter(description = "Updated fitness goal details") FitnessGoal updatedGoal) {
        
        try {
            FitnessGoal updated = fitnessGoalService.updateFitnessGoal(id, updatedGoal);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a fitness goal", description = "Remove a fitness goal entry using its ID")
    public ResponseEntity<Void> deleteFitnessGoal(
        @PathVariable @Parameter(description = "ID of the fitness goal") Long id) {
        
        fitnessGoalService.deleteFitnessGoal(id);
        return ResponseEntity.noContent().build();
    }
}
