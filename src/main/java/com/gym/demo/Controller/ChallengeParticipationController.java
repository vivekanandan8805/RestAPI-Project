package com.gym.demo.Controller;

import com.gym.demo.Model.ChallengeParticipation;
import com.gym.demo.Service.ChallengeParticipationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/challenge-participations")
@Tag(name = "Challenge Participation API", description = "APIs for managing challenge participation")
public class ChallengeParticipationController {

    @Autowired
    private ChallengeParticipationService challengeParticipationService;

    @GetMapping
    @Operation(summary = "Get all challenge participations", 
               description = "Retrieve a paginated list of challenge participations with sorting options")
    public Page<ChallengeParticipation> getAllChallengeParticipations(
        @RequestParam(defaultValue = "0") 
        @Parameter(description = "Page number (default is 0)") int page,

        @RequestParam(defaultValue = "10") 
        @Parameter(description = "Number of records per page (default is 10)") int size,

        @RequestParam(defaultValue = "id,asc") 
        @Parameter(description = "Sorting field and order (e.g., 'id,asc' or 'status,desc')") String sort) {

        // Split the sort parameter
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];

        // Define allowed sortable fields
        String[] allowedFields = {"id", "challengeName", "status"};  
        boolean isValidField = java.util.Arrays.asList(allowedFields).contains(sortField);

        // If the field is invalid, default to "id"
        if (!isValidField) {
            sortField = "id";
        }

        Sort.Direction direction = (sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc"))
                                    ? Sort.Direction.DESC 
                                    : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        return challengeParticipationService.getAllChallengeParticipations(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get challenge participation by ID", 
               description = "Retrieve a specific challenge participation record using its ID")
    public ResponseEntity<ChallengeParticipation> getChallengeParticipationById(
        @PathVariable @Parameter(description = "ID of the challenge participation") Long id) {
        
        Optional<ChallengeParticipation> challengeParticipation = challengeParticipationService.getChallengeParticipationById(id);
        return challengeParticipation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new challenge participation", 
               description = "Add a new challenge participation entry")
    public ChallengeParticipation createChallengeParticipation(
        @RequestBody @Parameter(description = "Challenge participation details") ChallengeParticipation challengeParticipation) {
        
        return challengeParticipationService.saveChallengeParticipation(challengeParticipation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a challenge participation", 
               description = "Modify an existing challenge participation record by ID")
    public ResponseEntity<ChallengeParticipation> updateChallengeParticipation(
        @PathVariable @Parameter(description = "ID of the challenge participation") Long id, 
        @RequestBody @Parameter(description = "Updated challenge participation details") ChallengeParticipation updatedParticipation) {
        
        try {
            ChallengeParticipation updated = challengeParticipationService.updateChallengeParticipation(id, updatedParticipation);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a challenge participation", 
               description = "Remove a challenge participation record using its ID")
    public ResponseEntity<Void> deleteChallengeParticipation(
        @PathVariable @Parameter(description = "ID of the challenge participation") Long id) {
        
        challengeParticipationService.deleteChallengeParticipation(id);
        return ResponseEntity.noContent().build();
    }
}
