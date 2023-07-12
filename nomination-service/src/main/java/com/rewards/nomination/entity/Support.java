package com.rewards.nomination.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supportId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nominationId")
    private NominationEntity nomination;

    private Long supportedBy;
}
