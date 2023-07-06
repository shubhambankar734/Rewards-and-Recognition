package com.rewards.nomination.controller;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.service.NominationService;
import com.rewards.nomination.util.NominationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nomination")
@CrossOrigin("*")
public class NominationController {

    @Autowired
    NominationService nominationService;

    @GetMapping("nominate/{id}")
    public NominationEntity getNomination(@PathVariable long id) {
        return nominationService.getNomination(id);
    }

    @PostMapping("nominate")
    public NominationEntity saveNomination(@RequestBody NominationEntity nomination) {
        return nominationService.saveNomination(nomination);
    }

    @PutMapping("updateNomination")
    public ResponseEntity<NominationEntity> updateNomination(@RequestBody NominationEntity nomination) {
        return new ResponseEntity<>(nominationService.saveNomination(nomination), HttpStatus.OK);
    }

    @DeleteMapping("deleteNomination/{id}")
    public ResponseEntity deleteNomination(@PathVariable Long id) {
        nominationService.deleteNominationById(id);
        return new ResponseEntity("Record Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("findAllByNominationStatus")
    public ResponseEntity<NominationEntity> findByNominationStatus(@RequestParam NominationStatusEnum status, @RequestParam("PageNo") int pageNo, @RequestParam("PageSize") int pageSize) {
        return new ResponseEntity(nominationService.findByNominationStatus(status, pageNo, pageSize), HttpStatus.OK);
    }


}
