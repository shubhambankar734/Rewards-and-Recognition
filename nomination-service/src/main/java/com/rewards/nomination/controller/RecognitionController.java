package com.rewards.nomination.controller;

import com.rewards.nomination.entity.Recognition;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.service.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recognize")
public class RecognitionController {

    @Autowired
    RecognitionService recognitionService;

    @PostMapping("/addComment")
    public ResponseEntity<Recognition> addComment (@RequestBody Recognition recognition){
        return new ResponseEntity(recognitionService.addComment(recognition), HttpStatus.CREATED);
    }

    @PutMapping("/editComment")
    public ResponseEntity<Recognition> editComment (@RequestBody Recognition recognition){
        return new ResponseEntity(recognitionService.addComment(recognition), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteComment/{recognitionId}")
    public ResponseEntity editComment (@PathVariable("recognitionId") Long recognitionId) throws CustomException {
        recognitionService.deleteComment(recognitionId);
        return new ResponseEntity("Record Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getAllRecognition/{nominationId}")
    public ResponseEntity<List<Recognition>> getAllRecognition(@PathVariable("nominationId") Long nominationId){
        return new ResponseEntity<>(recognitionService.getAllRecognitionByNominationId(nominationId), HttpStatus.OK);
    }
}
