package com.rewards.nomination.controller;

import com.rewards.nomination.entity.NominationEntity;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.service.NominationService;
import com.rewards.nomination.util.NominationStatusEnum;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nomination")
@CrossOrigin("*")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found")
})
public class NominationController {

    @Autowired
    NominationService nominationService;

    @GetMapping("nominate/{id}")
    public ResponseEntity<NominationEntity> getNomination(@PathVariable long id) throws CustomException {
        return new ResponseEntity<>(nominationService.getNomination(id), HttpStatus.OK);
    }

    @PostMapping("nominate")
    public ResponseEntity<NominationEntity> saveNomination(@RequestBody NominationEntity nomination) {
        return new ResponseEntity<>(nominationService.saveNomination(nomination), HttpStatus.CREATED);
    }

    @PutMapping("updateNomination")
    public ResponseEntity<NominationEntity> updateNomination(@RequestBody NominationEntity nomination) {
        return new ResponseEntity<>(nominationService.saveNomination(nomination), HttpStatus.OK);
    }

    @DeleteMapping("deleteNomination/{id}")
    public ResponseEntity deleteNomination(@PathVariable Long id) throws CustomException {
        nominationService.deleteNominationById(id);
        return new ResponseEntity("Record Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("findAllByNominationStatus")
    public ResponseEntity<NominationEntity> findByNominationStatus(@RequestParam NominationStatusEnum status, @RequestParam("PageNo") int pageNo, @RequestParam("PageSize") int pageSize) {
        return new ResponseEntity(nominationService.findByNominationStatus(status, pageNo, pageSize), HttpStatus.OK);
    }


}
