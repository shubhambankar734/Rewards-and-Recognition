package com.rewards.notification.controller;

import com.rewards.notification.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notification")
@CrossOrigin("*")
public class EmailSenderController {

    @Autowired
    EmailSenderService emailSenderService;

    @GetMapping("sendEmail")
    public String sendEmailNotification(@RequestParam String toRecipient, @RequestParam String feedback, @RequestParam String ccRecipient, @RequestParam String subject) {
        return emailSenderService.sendEmail(toRecipient, feedback, ccRecipient, subject);
    }
}
