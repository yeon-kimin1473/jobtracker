package com.example.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back.model.JobApplication;
import com.example.back.repository.JobApplicationRepository;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    // Add new job
    public JobApplication addJob(JobApplication job) {
        return jobApplicationRepository.save(job);
    }

    // Get all jobs
    public List<JobApplication> getAllJobs() {
        return jobApplicationRepository.findAll();
    }

    // Get jobs by user
    public List<JobApplication> getJobsByUser(Long userId) {
        return jobApplicationRepository.findByUserId(userId);
    }

    // Delete job
    public String deleteJob(Long id) {
        jobApplicationRepository.deleteById(id);
        return "Job deleted successfully";
    }

   // Get job by id
public JobApplication getJobById(Long id) {
    return jobApplicationRepository.findById(id).orElse(null);
}
}