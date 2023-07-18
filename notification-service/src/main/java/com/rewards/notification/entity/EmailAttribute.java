package com.rewards.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailAttribute {

    private String subject;
    private String body;
    private String toRecipient;
    private List<String> ccRecipient;

}