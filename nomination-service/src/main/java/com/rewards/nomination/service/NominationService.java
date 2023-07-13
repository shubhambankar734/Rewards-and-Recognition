package com.rewards.nomination.service;

import com.rewards.nomination.entity.EmailAttribute;
import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.repository.NominationRepository;
import com.rewards.nomination.util.NominationStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class NominationService {

    @Autowired
    NominationRepository nominationRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public NominationEntity saveNomination(NominationEntity nomination) {
        log.info("Inside saveNomination of Nomination Service");
        if (nomination.getNominationType().name().equals("SELF_NOMINATION")) {
            nomination.setNominationStatus(NominationStatusEnum.SUBMITTED);
        } else {
            nomination.setNominationStatus(NominationStatusEnum.RECOGNIZED);
        }
        nomination.setNominationCreatedDate(new Date());
        nomination.setNominationUpdatedDate(nomination.getNominationCreatedDate());

//        if (nomination.getNominatedTo() != 0l) {
//            UserDTO user = restTemplate.getForObject("http://localhost:8080/getUser/" + nomination.getNominatedTo(), UserDTO.class);
//            nomination.setNominatedManagerId(user.getManagerId());
//        }
        NominationEntity nominationEntity = nominationRepository.save(nomination);
        EmailAttribute emailAttribute = new EmailAttribute("Nomination for Reward",
                "Hi Kaushik , sending you the test email hopes it works!!!",
                "Kaushik.kapoor@publicissapient.com", "shubham.bankar@publicissapient.com");
        kafkaTemplate.send("NewTopic", emailAttribute);
        return nominationEntity;
    }

    public NominationEntity updateNomination(NominationEntity nomination) {
        log.info("Inside updateNomination of Nomination Service");
        nomination.setNominationUpdatedDate(new Date());
        NominationEntity nominationEntity = nominationRepository.save(nomination);
        return nominationEntity;
    }

    public NominationEntity getNomination(long id) throws CustomException {
        log.info("Inside getNomination by Id");
        return nominationRepository.findById(id).orElseThrow(() -> new CustomException("Nomination doesn't exist."));
    }

    public void deleteNominationById(Long id) throws CustomException {
        log.info("Inside deleteNominationById");
        getNomination(id);
        nominationRepository.deleteById(id);
    }

    public List<NominationEntity> findByNominationStatus(NominationStatusEnum nominationStatusEnum, int pageNo, int pageSize) {
        log.info("Inside findByNominationStatus");
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<NominationEntity> nominationEntityList = nominationRepository.findAllByNominationStatus(nominationStatusEnum, pageable);
        return nominationEntityList;
    }
}
