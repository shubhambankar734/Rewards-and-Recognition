package com.rewards.nomination.service;

import com.rewards.nomination.converter.RecognitionConverter;
import com.rewards.nomination.dto.RecognitionDTO;
import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.entity.Recognition;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.repository.NominationRepository;
import com.rewards.nomination.repository.RecognitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RecognitionService {

    @Autowired
    RecognitionRepository recognitionRepository;

    @Autowired
    NominationRepository nominationRepository;

    @Autowired
    RecognitionConverter recognitionConverter;

    @Autowired
    NominationService nominationService;

    public Recognition addComment(RecognitionDTO recognitionDTO) throws CustomException {
        log.info("Inside addComment in Nomination service");
        recognitionDTO.setRecognitionCreatedDate(new Date());
        recognitionDTO.setRecognitionUpdatedDate(recognitionDTO.getRecognitionCreatedDate());
        NominationEntity nominationEntity = nominationRepository.findById(recognitionDTO.getNominationId())
                .orElseThrow(() -> new CustomException("Nomination doesn't exist."));
        Recognition recognizedNomination = recognitionRepository.save(recognitionConverter.toRecognitionEntity(recognitionDTO, nominationEntity));
        nominationEntity.setRecognitionCount(nominationEntity.getRecognitionCount() + 1);
        nominationRepository.save(nominationEntity);
        return recognizedNomination;
    }

    public Recognition updateComment(RecognitionDTO recognitionDTO) throws CustomException {
        log.info("Inside updateComment");
        recognitionDTO.setRecognitionUpdatedDate(new Date());
        NominationEntity nominationEntity = nominationRepository.findById(recognitionDTO.getNominationId())
                .orElseThrow(() -> new CustomException("Nomination doesn't exist."));
        return recognitionRepository.save(recognitionConverter.toRecognitionEntity(recognitionDTO, nominationEntity));
    }

    public void deleteComment(Long recognitionId) throws CustomException {
        log.info("Inside deleteComment");
        Recognition recognition = recognitionRepository.findById(recognitionId).orElseThrow(() -> new CustomException("Record doesn't exist."));
        NominationEntity nominationEntity = recognition.getNomination();
        nominationEntity.setRecognitionCount(nominationEntity.getRecognitionCount() - 1);
        nominationRepository.save(nominationEntity);
        recognitionRepository.deleteById(recognitionId);
    }

    public List<Recognition> getAllRecognitionByNominationId(Long nominationId) throws CustomException {
        log.info("Inside getAllRecognitionByNominationId");
        nominationService.getNomination(nominationId);
        return recognitionRepository.findByNominationNominationIdOrderByRecognitionCreatedDateDesc(nominationId);
    }


}
