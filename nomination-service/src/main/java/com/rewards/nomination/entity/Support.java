package com.rewards.nomination.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nominationId", referencedColumnName = "nominationId")
    private NominationEntity nomination;

    private Long supportedBy;
}
