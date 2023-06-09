package com.rewards.nomination.service;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.payload.UserDTO;
import com.rewards.nomination.repository.NominationRepository;
import com.rewards.nomination.util.NominationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class NominationService {

    @Autowired
    NominationRepository nominationRepository;
    @Autowired
    private RestTemplate restTemplate;

    public NominationEntity saveNomination(NominationEntity nomination) {
        nomination.setNominationStatus(NominationStatusEnum.SUBMITTED);
        nomination.setSupportCount(1);
//        if (nomination.getNominatedTo() != 0l) {
//            UserDTO user = restTemplate.getForObject("http://localhost:8080/getUser/" + nomination.getNominatedTo(), UserDTO.class);
//            nomination.setNominatedManagerId(user.getManagerId());
//        }
        return nominationRepository.save(nomination);
    }

    public NominationEntity getNomination(long id) {
        Optional<NominationEntity> nominationOptional = nominationRepository.findById(id);
        return nominationOptional.isPresent() ? nominationOptional.get() : null;
    }
}
