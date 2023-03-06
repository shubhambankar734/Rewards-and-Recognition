package com.rewards.nomination.entity;

import com.rewards.nomination.util.NominationStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "nomination")
public class NominationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long nominationId;
    private String feedback;
    private long nominatedBy;
    private long nominatedTo;
    private long rewardId;

    private NominationStatusEnum nominationStatus;
    private long nominatedManagerId;
    private int supportCount;
}
