package com.rewards.nomination.controller;

import com.rewards.nomination.entity.Support;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("support")
public class SupportController {

    @Autowired
    SupportService supportService;

    @PostMapping("/addSupport")
    public ResponseEntity<Support> addSupport(@RequestBody Support support){
        Support supportNomination = supportService.addSupport(support);
        return new ResponseEntity<>(supportNomination, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteSupport/{supportId}")
    public ResponseEntity deleteSupport(@PathVariable("supportId") Long supportId) throws CustomException {
        supportService.deleteSupport(supportId);
        return new ResponseEntity("Record Deleted Successfully", HttpStatus.OK);
    }
}
