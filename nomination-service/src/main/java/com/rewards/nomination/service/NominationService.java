package com.rewards.nomination.service;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.repository.NominationRepository;
import com.rewards.nomination.util.NominationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NominationService {

    @Autowired
    NominationRepository nominationRepository;

    public NominationEntity saveNomination(NominationEntity nomination) {
        nomination.setNominationStatus(NominationStatusEnum.SUBMITTED);
        nomination.setSupportCount(1);
        return nominationRepository.save(nomination);
    }
}
