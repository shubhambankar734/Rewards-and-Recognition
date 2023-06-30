package com.rewards.nomination.service;

import com.rewards.nomination.entity.EmailAttribute;
import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.repository.NominationRepository;
import com.rewards.nomination.util.NominationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NominationService {

    @Autowired
    NominationRepository nominationRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public NominationEntity saveNomination(NominationEntity nomination) {
        nomination.setNominationStatus(NominationStatusEnum.SUBMITTED);
        nomination.setSupportCount(1);
        nomination.setNominationDate(new Date());
//        if (nomination.getNominatedTo() != 0l) {
//            UserDTO user = restTemplate.getForObject("http://localhost:8080/getUser/" + nomination.getNominatedTo(), UserDTO.class);
//            nomination.setNominatedManagerId(user.getManagerId());
//        }
        NominationEntity nominationEntity = nominationRepository.save(nomination);
        EmailAttribute emailAttribute = new EmailAttribute("Nomination for Reward", "Hi Kaushik , sending you the test email hopes it works!!!", "Kaushikkapoor1996@gmail.com", "shubhambankar734@gmail.com");
        kafkaTemplate.send("NewTopic", emailAttribute);
        return nominationEntity;
    }

    public NominationEntity getNomination(long id) {
        Optional<NominationEntity> nominationOptional = nominationRepository.findById(id);
        return nominationOptional.orElse(null);
    }

    public void deleteNominationById(Long id) {
        nominationRepository.deleteById(id);
    }

    public List<NominationEntity> findByNominationStatus(NominationStatusEnum nominationStatusEnum, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<NominationEntity> nominationEntityList = nominationRepository.findAllByNominationStatus(nominationStatusEnum, pageable);
        return nominationEntityList;
    }
}
