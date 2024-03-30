package com.stb.money_transfer_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCtrl {
    @GetMapping("/")
    public String ping() {
        return "Hello & Welcome to Money Transfer Service !!!";
    }
}
