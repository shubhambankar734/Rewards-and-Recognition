package com.rewards.rewards.repository;

import com.rewards.rewards.entity.Category;
import com.rewards.rewards.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findByCategoryCategoryId(Long categoryId);

    Reward findByRewardCode(String rewardCode);

}
