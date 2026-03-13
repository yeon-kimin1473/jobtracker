package com.example.back.controller;

import com.example.back.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        String status = jobDetails.get("status");
        String appliedDate = jobDetails.get("appliedDate");

        String result = aiService.predictSuccess(company, role, status, appliedDate);
        return Map.of("prediction", result);
    }
}