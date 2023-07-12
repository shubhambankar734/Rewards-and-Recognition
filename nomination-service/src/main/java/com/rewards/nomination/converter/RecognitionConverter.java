package com.rewards.nomination.converter;

import com.rewards.nomination.dto.RecognitionDTO;
import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.entity.Recognition;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecognitionConverter {
    public RecognitionDTO toRecognitionDto(Recognition recognition) {
        RecognitionDTO dto = new RecognitionDTO();
        dto.setNominationId(recognition.getNomination().getNominationId());
        BeanUtils.copyProperties(recognition, dto);
        return dto;
    }

    public Recognition toRecognitionEntity(RecognitionDTO recognitionDTO, NominationEntity nomination) {
        Recognition recognition = new Recognition();
        recognition.setNomination(nomination);
        BeanUtils.copyProperties(recognitionDTO, recognition);
        return recognition;
    }

    public List<RecognitionDTO> toRecognitionDtoList(List<Recognition> recognitionList) {
        return recognitionList.stream().map(this::toRecognitionDto).collect(Collectors.toList());
    }
}
