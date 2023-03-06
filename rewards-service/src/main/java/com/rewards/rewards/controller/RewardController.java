package com.rewards.rewards.controller;

import com.rewards.rewards.entity.Reward;
import com.rewards.rewards.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/getReward/{id}")
    public Reward getReward(@PathVariable("id") Long id){
        return rewardService.getReward(id);
    }

    @PostMapping("/saveReward")
    public Reward saveReward(@RequestBody Reward reward){
        return rewardService.saveReward(reward);
    }
}
