package com.rewards.nomination.converter;

import com.rewards.nomination.dto.SupportDTO;
import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.entity.Support;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupportConverter {

    public SupportDTO toSupportDto(Support support) {
        SupportDTO dto = new SupportDTO();
        dto.setNominationId(support.getNomination().getNominationId());
        BeanUtils.copyProperties(support, dto);
        return dto;
    }

    public Support toSupportEntity(SupportDTO supportDTO, NominationEntity nomination) {
        Support support = new Support();
        support.setNomination(nomination);
        BeanUtils.copyProperties(supportDTO, support);
        return support;
    }

    public List<SupportDTO> toSupportDtoList(List<Support> supportList) {
        return supportList.stream().map(this::toSupportDto).collect(Collectors.toList());
    }
}
