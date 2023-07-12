package com.rewards.nomination.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rewards.nomination.util.NominationStatusEnum;
import com.rewards.nomination.util.NominationTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "nomination")
public class NominationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nominationId;
    private NominationTypeEnum nominationType;
    private String feedback;
    private Long nominatedBy;
    private Long nominatedTo;
    private Long rewardId;
    private NominationStatusEnum nominationStatus;
    private Long nominatedManagerId;
    private long supportCount;
    private long recognitionCount;
    private String reviewerFeedback;
    private Date nominationDate;
    @OneToMany(mappedBy = "nomination")
    @JsonIgnore
    private List<Support> supportList;
    @OneToMany(mappedBy = "nomination")
    @JsonIgnore
    private List<Recognition> recognitionList;
}
