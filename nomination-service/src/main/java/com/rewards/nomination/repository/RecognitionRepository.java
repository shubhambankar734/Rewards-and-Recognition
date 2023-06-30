package com.rewards.nomination.repository;

import com.rewards.nomination.entity.Recognition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecognitionRepository extends JpaRepository<Recognition, Long> {

    List<Recognition> findByNominationNominationIdOrderByRecognitionDateDesc(Long nominationId);
}
