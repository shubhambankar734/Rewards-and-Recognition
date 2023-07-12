package com.rewards.nomination.service;

import com.rewards.nomination.converter.SupportConverter;
import com.rewards.nomination.dto.SupportDTO;
import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.entity.Support;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.repository.NominationRepository;
import com.rewards.nomination.repository.SupportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SupportService {

    @Autowired
    SupportRepository supportRepository;

    @Autowired
    NominationRepository nominationRepository;

    @Autowired
    SupportConverter supportConverter;

    @Autowired
    NominationService nominationService;

    public Support addSupport(SupportDTO supportDTO) throws CustomException {
        log.info("Inside addSupport in Nomination service");
        NominationEntity nominationEntity = nominationRepository.findById(supportDTO.getNominationId())
                .orElseThrow(() -> new CustomException("Nomination doesn't exist."));
        Support supportNomination = supportRepository.save(supportConverter.toSupportEntity(supportDTO,nominationEntity));
//        NominationEntity nominationEntity = supportNomination.getNomination();
        nominationEntity.setSupportCount(nominationEntity.getSupportCount() + 1);
        nominationRepository.save(nominationEntity);
        return supportNomination;
    }

    public void deleteSupport(Long supportId) throws CustomException {
        log.info("Inside deleteSupport in Nomination service");
        Support supportNomination = supportRepository.findById(supportId).orElseThrow(() -> new CustomException("Record doesn't exist."));
        NominationEntity nominationEntity = supportNomination.getNomination();
        nominationEntity.setSupportCount(nominationEntity.getSupportCount() - 1);
        nominationRepository.save(nominationEntity);
        supportRepository.deleteById(supportId);
    }


    public List<Support> getAllSupport(Long nominationId) throws CustomException {
        nominationService.getNomination(nominationId);
        return supportRepository.findByNominationNominationId(nominationId);
    }
}
