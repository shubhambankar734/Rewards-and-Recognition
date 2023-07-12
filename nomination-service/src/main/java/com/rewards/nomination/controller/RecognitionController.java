package com.rewards.nomination.controller;

import com.rewards.nomination.converter.RecognitionConverter;
import com.rewards.nomination.dto.RecognitionDTO;
import com.rewards.nomination.exception.CustomException;
import com.rewards.nomination.service.RecognitionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recognize")
@CrossOrigin("*")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "Not found")
})
public class RecognitionController {

    @Autowired
    RecognitionService recognitionService;

    @Autowired
    RecognitionConverter recognitionConverter;

    @PostMapping("/addComment")
    public ResponseEntity<RecognitionDTO> addComment(@RequestBody RecognitionDTO recognitionDTO) throws CustomException {
        return new ResponseEntity(recognitionConverter
                .toRecognitionDto(recognitionService.addComment(recognitionDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/editComment")
    public ResponseEntity<RecognitionDTO> editComment(@RequestBody RecognitionDTO recognitionDTO) throws CustomException {
        return new ResponseEntity(recognitionConverter
                .toRecognitionDto(recognitionService.updateComment(recognitionDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{recognitionId}")
    public ResponseEntity deleteComment(@PathVariable("recognitionId") Long recognitionId) throws CustomException {
        recognitionService.deleteComment(recognitionId);
        return new ResponseEntity("Record Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getAllRecognition/{nominationId}")
    public ResponseEntity<List<RecognitionDTO>> getAllRecognition(@PathVariable("nominationId") Long nominationId) throws CustomException {
        return new ResponseEntity<>(recognitionConverter.toRecognitionDtoList
                (recognitionService.getAllRecognitionByNominationId(nominationId)), HttpStatus.OK);
    }
}
