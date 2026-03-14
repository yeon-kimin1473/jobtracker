package com.example.back.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AiService {

    @Value("${HUGGINGFACE_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String predictSuccess(String company, String role, String jobDescription) {

        String prompt = "You are a career coach. Analyze this job application:\n\n" +
                "Company: " + company + "\n" +
                "Role: " + role + "\n\n" +
                "Job Description:\n" + jobDescription + "\n\n" +
                "Give a match score out of 100 and 3-4 bullet points.\n" +
                "Format EXACTLY like this:\n" +
                "Match Score: [number]/100\n" +
                "- [reason 1]\n" +
                "- [reason 2]\n" +
                "- [reason 3]\n" +
                "Verdict: [one line verdict]";

        String url = "https://router.huggingface.co/v1/chat/completions";

        Map<String, Object> body = new HashMap<>();
        body.put("model", "meta-llama/Llama-3.1-8B-Instruct");
        body.put("temperature", 0);

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.add(userMessage);
        body.put("messages", messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            Map responseBody = response.getBody();
            List choices = (List) responseBody.get("choices");
            Map firstChoice = (Map) choices.get(0);
            Map message = (Map) firstChoice.get("message");
            return message.get("content").toString().trim();
        } catch (Exception e) {
            return "Match Score: 0/100\n- Error: " + e.getMessage() + "\nVerdict: Could not analyze.";
        }
    }
}