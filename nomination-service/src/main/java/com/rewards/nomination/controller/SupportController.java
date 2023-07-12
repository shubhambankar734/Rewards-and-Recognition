package com.rewards.nomination.controller;

import com.rewards.nomination.converter.SupportConverter;
import com.rewards.nomination.dto.SupportDTO;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.service.SupportService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("support")
@CrossOrigin("*")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found")
})
public class SupportController {

    @Autowired
    SupportService supportService;

    @Autowired
    SupportConverter supportConverter;

    @PostMapping("/addSupport")
    public ResponseEntity<SupportDTO> addSupport(@RequestBody SupportDTO supportDTO) throws CustomException {
        return new ResponseEntity<>(supportConverter.toSupportDto
                (supportService.addSupport(supportDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteSupport/{supportId}")
    public ResponseEntity deleteSupport(@PathVariable("supportId") Long supportId) throws CustomException {
        supportService.deleteSupport(supportId);
        return new ResponseEntity("Record Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getAllSupportByNominationId/{nominationId}")
    public ResponseEntity<List<SupportDTO>> getAllSupport(@PathVariable Long nominationId) throws CustomException {
        return new ResponseEntity<>(supportConverter.toSupportDtoList(
                supportService.getAllSupport(nominationId)), HttpStatus.OK);
    }
}
