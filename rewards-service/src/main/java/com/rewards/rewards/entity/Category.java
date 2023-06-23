package com.rewards.rewards.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryCode;
    private String categoryName;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rewardId", referencedColumnName = "rewardId")
    private List<Reward> rewardList;
}
