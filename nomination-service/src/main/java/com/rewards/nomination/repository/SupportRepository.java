package com.rewards.nomination.repository;

import com.rewards.nomination.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
    List<Support> findByNominationNominationId(Long nominationId);
}
