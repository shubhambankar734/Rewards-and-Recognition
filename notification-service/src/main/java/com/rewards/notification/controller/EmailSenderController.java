package com.rewards.notification.controller;

import com.rewards.notification.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification")
public class EmailSenderController {

    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("sendEmail")
    public String sendEmailNotification(@RequestParam String toRecipient, @RequestParam String feedback, @RequestParam String ccRecipient, @RequestParam String subject) {
        return emailSenderService.sendEmail(toRecipient, feedback, ccRecipient, subject);
    }
}
