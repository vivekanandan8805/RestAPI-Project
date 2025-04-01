package com.gym.demo.Repository;
import com.gym.demo.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChallengeParticipationRepository extends JpaRepository<ChallengeParticipation, Long>, PagingAndSortingRepository<ChallengeParticipation, Long> {
}
