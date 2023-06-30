package com.rewards.nomination.service;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.entity.Recognition;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.repository.NominationRepository;
import com.rewards.nomination.repository.RecognitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecognitionService {

    @Autowired
    RecognitionRepository recognitionRepository;

    @Autowired
    NominationRepository nominationRepository;

    public Recognition addComment(Recognition recognition) {
        recognition.setRecognitionDate(new Date());
        Recognition recognizedNomination = recognitionRepository.save(recognition);
        NominationEntity nominationEntity = recognizedNomination.getNomination();
        nominationEntity.setSupportCount(nominationEntity.getRecognitionCount() + 1);
        nominationRepository.save(nominationEntity);
        return recognizedNomination;
    }

    public void deleteComment(Long recognitionId) throws CustomException{
        Recognition recognition = recognitionRepository.findById(recognitionId).orElseThrow(() -> new CustomException("Record doesnot exist"));
        NominationEntity nominationEntity = recognition.getNomination();
        nominationEntity.setSupportCount(nominationEntity.getRecognitionCount() - 1);
        nominationRepository.save(nominationEntity);
        recognitionRepository.deleteById(recognitionId);
    }

    public List<Recognition> getAllRecognitionByNominationId(Long nominationId) {
        return recognitionRepository.findByNominationNominationIdOrderByRecognitionDateDesc(nominationId);
    }
}
