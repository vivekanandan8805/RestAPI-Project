package com.gym.demo.Service;
import com.gym.demo.Model.*;
import com.gym.demo.Repository.ChallengeParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChallengeParticipationService {
    @Autowired
    private ChallengeParticipationRepository challengeParticipationRepository;

    public Page<ChallengeParticipation> getAllChallengeParticipations(Pageable pageable) {
        return challengeParticipationRepository.findAll(pageable);
    }

    public Optional<ChallengeParticipation> getChallengeParticipationById(Long id) {
        return challengeParticipationRepository.findById(id);
    }

    public ChallengeParticipation saveChallengeParticipation(ChallengeParticipation challengeParticipation) {
        return challengeParticipationRepository.save(challengeParticipation);
    }

    public ChallengeParticipation updateChallengeParticipation(Long id, ChallengeParticipation updatedParticipation) {
        return challengeParticipationRepository.findById(id).map(existingParticipation -> {
            existingParticipation.setUser(updatedParticipation.getUser());
            existingParticipation.setChallengeName(updatedParticipation.getChallengeName());
            existingParticipation.setStatus(updatedParticipation.getStatus());
            return challengeParticipationRepository.save(existingParticipation);
        }).orElseThrow(() -> new RuntimeException("ChallengeParticipation not found with id " + id));
    }

    public void deleteChallengeParticipation(Long id) {
        challengeParticipationRepository.deleteById(id);
    }
}
