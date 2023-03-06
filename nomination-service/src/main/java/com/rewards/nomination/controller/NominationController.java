package com.rewards.nomination.controller;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nomination")
public class NominationController {

    @Autowired
    NominationService nominationService;

    @GetMapping("nominate/{id}")
    public NominationEntity getNomination(@PathVariable long id){
        return nominationService.getNomination(id);
    }
    @PostMapping("nominate")
    public NominationEntity saveNomination(@RequestBody NominationEntity nomination){
        return nominationService.saveNomination(nomination);
    }




}
