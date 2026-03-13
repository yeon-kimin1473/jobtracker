package com.example.back.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.List;

@Service
public class AiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create();

    public String predictSuccess(String company, String role, String status, String appliedDate) {

        String prompt = "You are a job application coach. Analyze this job application and predict success:\n" +
                "Company: " + company + "\n" +
                "Role: " + role + "\n" +
                "Status: " + status + "\n" +
                "Applied Date: " + appliedDate + "\n\n" +
                "Give a success score out of 100 and 3-4 short bullet points explaining why.\n" +
                "Format your response exactly like this:\n" +
                "Score: [number]/100\n" +
                "- [reason 1]\n" +
                "- [reason 2]\n" +
                "- [reason 3]\n" +
                "- [reason 4]";

        Map<String, Object> requestBody = Map.of(
            "contents", List.of(
                Map.of("parts", List.of(
                    Map.of("text", prompt)
                ))
            )
        );

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

        Map response = webClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        try {
            List candidates = (List) response.get("candidates");
            Map candidate = (Map) candidates.get(0);
            Map content = (Map) candidate.get("content");
            List parts = (List) content.get("parts");
            Map part = (Map) parts.get(0);
            return (String) part.get("text");
        } catch (Exception e) {
            return "Score: 50/100\n- Could not analyze at this time. Please try again.";
        }
    }
}