package com.rewards.apachekafkaproducer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaController {

    @Autowired
    KafkaTemplate<String , Object> kafkaTemplate;

    public static final String TOPIC = "NewTopic";

    @PostMapping("/publish")
    public String publishMessage(@RequestBody Object message){
        kafkaTemplate.send(TOPIC,message);
        return "Published Successfully";
    }
}
