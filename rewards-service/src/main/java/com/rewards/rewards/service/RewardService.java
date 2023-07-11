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

    public Reward getRewardById(Long id) throws CustomException {
        return rewardRepository.findById(id).orElseThrow(() -> new CustomException("Reward does not exist."));
    }

    public Reward saveReward(RewardDTO rewardDTO) throws CustomException {
        Reward existingReward = rewardRepository.findByRewardCode(rewardDTO.getRewardCode());
        if (null != existingReward)
            new CustomException("Reward code already exist.");
        Category category = categoryRepository.findById(rewardDTO.getCategoryId()).
                orElseThrow(() -> new CustomException("Category does not exist."));
        return rewardRepository.save(rewardConverter.toRewardEntity(rewardDTO, category));
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public void deleteByRewardId(Long id) throws CustomException {
        getRewardById(id);
        rewardRepository.deleteById(id);
    }

    public Category getCategoryById(Long id) throws CustomException {
        return categoryRepository.findById(id).orElseThrow(() -> new CustomException("Category does not exist."));
    }

    public Category saveCategory(Category category) {
        Category existingCategory = categoryRepository.findByCategoryCode(category.getCategoryCode());
        if (null != existingCategory)
            new CustomException("Category code already exist.");
        return categoryRepository.save(category);
    }

    public void deleteByCategoryId(Long id) throws CustomException {
        getCategoryById(id);
        categoryRepository.deleteById(id);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public List<Reward> getAllRewardsByCategoryId(Long categoryId) throws CustomException {
        getCategoryById(categoryId);
        return rewardRepository.findByCategoryCategoryId(categoryId);
    }
}
