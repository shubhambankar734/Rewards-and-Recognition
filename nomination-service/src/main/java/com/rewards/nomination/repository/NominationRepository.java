package com.rewards.nomination.repository;

import com.rewards.nomination.entity.NominationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NominationRepository extends JpaRepository<NominationEntity,Long> {

}
