package com.example.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.model.JobApplication;
import com.example.back.service.JobApplicationService;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    // Add job
    @PostMapping("/add")
    public JobApplication addJob(@RequestBody JobApplication job) {
        return jobApplicationService.addJob(job);
    }

    // Get all jobs
    @GetMapping("/all")
    public List<JobApplication> getAllJobs() {
        return jobApplicationService.getAllJobs();
    }

    // Get jobs by user
    @GetMapping("/user/{userId}")
    public List<JobApplication> getJobsByUser(@PathVariable Long userId) {
        return jobApplicationService.getJobsByUser(userId);
    }

    // Delete job
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        return jobApplicationService.deleteJob(id);
    }

    // Update status
@PutMapping("/{id}/status")
public String updateStatus(@PathVariable Long id, @RequestParam String status) {
    JobApplication job = jobApplicationService.getJobById(id);
    job.setStatus(status);
    jobApplicationService.addJob(job);
    return "Status updated successfully";
}
}