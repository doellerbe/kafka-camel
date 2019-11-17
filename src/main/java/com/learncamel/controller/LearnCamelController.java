package com.learncamel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearnCamelController {

    @Autowired
    Environment env;

    @RequestMapping("/home")
    public String getResponse() {
        return "Hello, World!" + env.getProperty("message.response");
    }
}
