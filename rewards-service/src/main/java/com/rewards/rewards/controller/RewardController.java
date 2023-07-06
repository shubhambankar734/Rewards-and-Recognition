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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reward")
@CrossOrigin("*")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @Autowired
    private RewardConverter rewardConverter;

    @GetMapping("/getreward/{id}")
    public RewardDTO getReward(@PathVariable("id") Long id){
        Reward reward = rewardService.getRewardById(id);
        return rewardConverter.toRewardDTO(reward);
    }

    @PostMapping("/savereward")
    public RewardDTO saveReward(@RequestBody RewardDTO reward) throws CustomException {
      return rewardConverter.toRewardDTO(rewardService.saveReward(reward));
    }

    @PutMapping("/updatereward")
    public RewardDTO updateReward(@RequestBody RewardDTO reward) throws CustomException {
        return rewardConverter.toRewardDTO(rewardService.saveReward(reward));
    }

    @GetMapping("/getallreward")
    public ResponseEntity<List<Reward>> getAllRewards(){
        return new ResponseEntity<>(rewardService.getAllRewards(), HttpStatus.OK);
    }

    @DeleteMapping("/deletereward/{id}")
    public ResponseEntity<Object> deleteByRewardId(@PathVariable("id") Long id){
        rewardService.deleteByRewardId(id);
        return new ResponseEntity<>("Record Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getcategory/{id}")
    public Category getCategoryById(@PathVariable("id") Long id){
        return rewardService.getCategoryById(id);
    }

    @PostMapping("/savecategory")
    public Category saveCategory(@RequestBody Category category){
        return rewardService.saveCategory(category);
    }

    @PutMapping("/updatecategory")
    public Category updateCategory(@RequestBody Category category){
        return rewardService.saveCategory(category);
    }

    @GetMapping("/getallcategory")
    public ResponseEntity<List<Category>> getAllCategory(){
        return new ResponseEntity<>(rewardService.getAllCategory(), HttpStatus.OK);
    }

    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<Object> deleteByCategoryId(@PathVariable("id") Long id){
        rewardService.deleteByCategoryId(id);
        return new ResponseEntity<>("Record Deleted Successfully", HttpStatus.OK);
    }
}
