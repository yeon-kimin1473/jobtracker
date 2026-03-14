package com.example.back.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.service.AiService;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/predict")
    public Map<String, String> predict(@RequestBody Map<String, String> jobDetails) {
        String company = jobDetails.get("company");
        String role = jobDetails.get("role");
        String jobDescription = jobDetails.get("jobDescription");
        String result = aiService.predictSuccess(company, role, jobDescription);
        return Map.of("prediction", result);
    }
}