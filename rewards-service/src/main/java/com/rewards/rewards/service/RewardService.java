package com.rewards.rewards.service;

import com.rewards.rewards.entity.Category;
import com.rewards.rewards.entity.Reward;
import com.rewards.rewards.repository.CategoryRepository;
import com.rewards.rewards.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Reward getRewardById(Long id) {
        Optional<Reward> reward = rewardRepository.findById(id);
        return reward.isPresent() ? reward.get() : null;
    }

    public Reward saveReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public void deleteByRewardId(Long id) {
        rewardRepository.deleteById(id);
    }

    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.isPresent() ? category.get() : null;
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteByCategoryId(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

}
