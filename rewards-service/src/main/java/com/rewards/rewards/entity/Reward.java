package com.rewards.rewards.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rewardId;
    private String rewardName;
    private String rewardCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId", referencedColumnName = "categoryId")
    private Category category;

}
