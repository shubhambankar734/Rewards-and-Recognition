package com.rewards.rewards.service;

import com.rewards.rewards.converter.RewardConverter;
import com.rewards.rewards.dto.RewardDTO;
import com.rewards.rewards.entity.Category;
import com.rewards.rewards.entity.Reward;
import com.rewards.rewards.exception.CustomException;
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

    @Autowired
    private RewardConverter rewardConverter;

    public Reward getRewardById(Long id) {
        return rewardRepository.findById(id).orElse(null);
    }

    public Reward saveReward(RewardDTO reward) throws CustomException {
        Optional<Category> categoryOptional = categoryRepository.findById(reward.getCategoryId());
        Category category = categoryOptional.orElseThrow(() -> new CustomException("Category does not exist."));
        return rewardRepository.save(rewardConverter.toRewardEntity(reward , category));
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

    public List<Reward> getAllRewardsByCategoryId(Long categoryId) {
        return rewardRepository.findByCategoryCategoryId(categoryId);
    }
}
