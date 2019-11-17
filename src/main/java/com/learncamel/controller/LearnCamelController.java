package com.learncamel.controller;

import com.learncamel.kafka.producer.ProducerKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearnCamelController {

    @Autowired
    Environment env;

    @Autowired
    ProducerKafka producerKafka;

    @RequestMapping("/home")
    public String getResponse(@RequestParam("input") String value) {
        producerKafka.sendMessage(value);
        return "Hello, World!" + env.getProperty("message.response");
    }
}
