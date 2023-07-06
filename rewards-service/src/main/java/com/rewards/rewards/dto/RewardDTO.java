package com.rewards.rewards.dto;

import com.rewards.rewards.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardDTO {
    private long rewardId;
    private String rewardName;
    private String rewardCode;
    private long categoryId;
}
