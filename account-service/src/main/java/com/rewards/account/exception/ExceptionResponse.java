package com.rewards.account.exception;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ExceptionResponse {

    private String message;
    private LocalDateTime dateTime;
}
