package com.rewards.nomination.service;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.entity.Support;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.repository.NominationRepository;
import com.rewards.nomination.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportService {

    @Autowired
    SupportRepository supportRepository;

    @Autowired
    NominationRepository nominationRepository;

    public Support addSupport(Support support) {
        Support supportNomination = supportRepository.save(support);
        NominationEntity nominationEntity = supportNomination.getNomination();
        nominationEntity.setSupportCount(nominationEntity.getSupportCount() + 1);
        nominationRepository.save(nominationEntity);
        return supportNomination;
    }

    public void deleteSupport(Long supportId) throws CustomException {
        Support supportNomination = supportRepository.findById(supportId).orElseThrow(() -> new CustomException("Record doesnot exist"));
        NominationEntity nominationEntity = supportNomination.getNomination();
        nominationEntity.setSupportCount(nominationEntity.getSupportCount() - 1);
        nominationRepository.save(nominationEntity);
        supportRepository.deleteById(supportId);
    }
}
