package com.rewards.nomination.repository;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.util.NominationStatusEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NominationRepository extends JpaRepository<NominationEntity,Long> {

    List<NominationEntity> findAllByNominationStatus(NominationStatusEnum nominationStatusEnum, Pageable pageable);
}
