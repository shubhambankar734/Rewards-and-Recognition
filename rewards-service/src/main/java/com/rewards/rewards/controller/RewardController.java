package com.rewards.rewards.controller;

import com.rewards.rewards.converter.RewardConverter;
import com.rewards.rewards.dto.RewardDTO;
import com.rewards.rewards.entity.Category;
import com.rewards.rewards.entity.Reward;
import com.rewards.rewards.exception.CustomException;
import com.rewards.rewards.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/reward")
@CrossOrigin("*")
@Validated
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @Autowired
    private RewardConverter rewardConverter;

    @GetMapping("/getReward/{id}")
    public ResponseEntity<RewardDTO> getReward(@PathVariable("id") Long id) throws CustomException {
        Reward reward = rewardService.getRewardById(id);
        return new ResponseEntity<>(rewardConverter.toRewardDTO(reward), HttpStatus.OK);
    }

    @PostMapping("/saveReward")
    public ResponseEntity<RewardDTO> saveReward(@RequestBody RewardDTO reward) throws CustomException {
        return new ResponseEntity<>(rewardConverter.toRewardDTO(rewardService.saveReward(reward)), HttpStatus.CREATED);
    }

    @PutMapping("/updateReward")
    public ResponseEntity<RewardDTO> updateReward(@RequestBody RewardDTO reward) throws CustomException {
        return new ResponseEntity<>(rewardConverter.toRewardDTO(rewardService.saveReward(reward)), HttpStatus.OK);
    }

    @GetMapping("/getAllReward")
    public ResponseEntity<List<Reward>> getAllRewards() {
        return new ResponseEntity<>(rewardService.getAllRewards(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteReward/{id}")
    public ResponseEntity<Void> deleteByRewardId(@PathVariable("id") Long id) throws CustomException {
        rewardService.deleteByRewardId(id);
        return new ResponseEntity("Record Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) throws CustomException {
        return new ResponseEntity<>(rewardService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return new ResponseEntity<>(rewardService.saveCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(rewardService.saveCategory(category), HttpStatus.OK);
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<Category>> getAllCategory() {
        return new ResponseEntity<>(rewardService.getAllCategory(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Object> deleteByCategoryId(@PathVariable("id") Long id) throws CustomException {
        rewardService.deleteByCategoryId(id);
        return new ResponseEntity<>("Record Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getAllRewardsByCategoryId/{categoryId}")
    public ResponseEntity<List<Reward>> getAllRewardsByCategoryId(@PathVariable Long categoryId) throws CustomException {
        return new ResponseEntity<>(rewardService.getAllRewardsByCategoryId(categoryId), HttpStatus.OK);
    }
}
