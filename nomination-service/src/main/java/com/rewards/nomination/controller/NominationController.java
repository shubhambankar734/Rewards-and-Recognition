package com.rewards.nomination.controller;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NominationController {

    @Autowired
    NominationService nominationService;

    @PostMapping("nominate")
    public NominationEntity saveNomination(@RequestBody NominationEntity nomination){
        return nominationService.saveNomination(nomination);
    }




}
