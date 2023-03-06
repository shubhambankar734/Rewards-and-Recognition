package com.rewards.rewards.service;

import com.rewards.rewards.entity.Reward;
import com.rewards.rewards.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public Reward getReward(Long id) {
        Optional<Reward> reward = rewardRepository.findById(id);
        return reward.isPresent()?reward.get():null;
    }

    public Reward saveReward(Reward reward) {
        return rewardRepository.save(reward);
    }
}
