package com.rewards.nomination.entity;

import com.rewards.nomination.util.NominationStatusEnum;
import com.rewards.nomination.util.NominationTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "nomination")
public class NominationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long nominationId;
    private NominationTypeEnum nominationType;
    private String feedback;
    private long nominatedBy;
    private long nominatedTo;
    private long rewardId;
    private NominationStatusEnum nominationStatus;
    private long nominatedManagerId;
    private int supportCount;
    private int recognitionCount;
    private String reviewerFeedback;

    private Date nominationDate;
}
