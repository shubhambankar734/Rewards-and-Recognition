package com.rewards.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewards.notification.entity.EmailAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    JavaMailSender mailSender;

    public String sendEmail(String toRecipient, String body, List<String> ccRecipient, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toRecipient);
        simpleMailMessage.setText(body);
        simpleMailMessage.setCc(ccRecipient.toArray(new String[ccRecipient.size()]));
        simpleMailMessage.setSubject(subject);
        mailSender.send(simpleMailMessage);
        return "Email Send Successfully...";
    }

    @KafkaListener(topics = "NewTopic", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmailAttribute emailAttribute = objectMapper.readValue(message, EmailAttribute.class);
        sendEmail(emailAttribute.getToRecipient(), emailAttribute.getBody(), emailAttribute.getCcRecipient(), emailAttribute.getSubject());
    }
}
