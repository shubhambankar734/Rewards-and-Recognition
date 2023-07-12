package com.rewards.nomination.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SupportDTO {

    private long supportId;
    private long nominationId;
    private long supportedBy;
}
