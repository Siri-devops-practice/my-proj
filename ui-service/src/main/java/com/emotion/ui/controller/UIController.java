package com.emotion.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*")
public class UIController {

    @GetMapping("/api/health")
    public String health() {
        return "UI Service is running!";
    }

    @GetMapping("/api/info")
    public String getInfo() {
        return "Emotion App - Creative Message UI v1.0.0";
    }
}

@Controller
class PageController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
