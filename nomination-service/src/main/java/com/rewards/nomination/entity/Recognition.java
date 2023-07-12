package com.rewards.nomination.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table
public class Recognition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recognitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nominationId")
    private NominationEntity nomination;

    private Long recognizedBy;
    private String feedback;
    private Date recognitionCreatedDate;
    private Date recognitionUpdatedDate;
}
