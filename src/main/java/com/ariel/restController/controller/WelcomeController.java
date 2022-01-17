package com.ariel.restController.controller;

import com.ariel.restController.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    WelcomeService service;

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return service.getWelcomeMessage();
    }
}
