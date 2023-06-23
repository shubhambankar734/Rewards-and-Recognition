package com.rewards.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailAttribute {

    private String subject;
    private String body;
    private String toRecipient;
    private String ccRecipient;

    @Override
    public String toString() {
        return "EmailAttribute{" +
                "subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", toEmail='" + toRecipient + '\'' +
                ", ccEmail='" + ccRecipient + '\'' +
                '}';
    }
}