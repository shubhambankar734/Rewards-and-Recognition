package com.rewards.nomination.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class RecognitionDTO {

    private long recognitionId;
    private long nominationId;
    private long recognizedBy;
    private String feedback;
    private Date recognitionCreatedDate;
    private Date recognitionUpdatedDate;

}
