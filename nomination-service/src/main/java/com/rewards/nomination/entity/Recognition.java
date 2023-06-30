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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recognitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nominationId", referencedColumnName = "nominationId")
    private NominationEntity nomination;

    private Long recognizedBy;
    private String feedback;
    private Date recognitionDate;
}
