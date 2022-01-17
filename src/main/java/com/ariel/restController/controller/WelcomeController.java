package com.ariel.restController.controller;

import com.ariel.restController.configuration.ConfigBean;
import com.ariel.restController.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService service;

    @Autowired
    private ConfigBean configBean;

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return service.getWelcomeMessage();
    }

    @GetMapping("/dynamic-config")
    public String dynamicConfig() {
        StringBuilder builder = new StringBuilder();
        builder.append(configBean.getMessage());
        builder.append("\n");
        if (configBean.getValue()) {
            builder.append(configBean.getNumber());
        }
        return builder.toString();
    }
}
