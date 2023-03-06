package com.rewards.nomination.service;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.repository.NominationRepository;
import com.rewards.nomination.util.NominationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NominationService {

    @Autowired
    NominationRepository nominationRepository;

    public NominationEntity saveNomination(NominationEntity nomination) {
        nomination.setNominationStatus(NominationStatusEnum.SUBMITTED);
        nomination.setSupportCount(1);
        return nominationRepository.save(nomination);
    }

    public NominationEntity getNomination(long id) {
        Optional<NominationEntity> nominationOptional = nominationRepository.findById(id);
        return nominationOptional.isPresent() ? nominationOptional.get() : null;
    }
}
