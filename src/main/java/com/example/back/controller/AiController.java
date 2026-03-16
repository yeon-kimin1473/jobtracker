package com.example.back.controller;

import java.util.Map;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.back.service.AiService;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/predict")
    public Map<String, String> predict(
            @RequestParam("company") String company,
            @RequestParam("role") String role,
            @RequestParam("jobDescription") String jobDescription,
            @RequestParam("resume") MultipartFile resume) {

        try {
            // Extract text from PDF
            PDDocument document = Loader.loadPDF(resume.getBytes());
            PDFTextStripper stripper = new PDFTextStripper();
            String resumeText = stripper.getText(document);
            document.close();

            String result = aiService.predictSuccess(company, role, jobDescription, resumeText);
            return Map.of("prediction", result);

        } catch (Exception e) {
            return Map.of("prediction", "Could not read resume. Please try again.");
        }
    }
}