package com.example.back.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String role;
    private String status;
    private LocalDate appliedDate;
    private Long userId;
    private LocalDate deadline;
    private LocalDate followUpDate;

    public JobApplication() {}

    public JobApplication(String company, String role, String status, LocalDate appliedDate, Long userId, LocalDate deadline, LocalDate followUpDate) {
        this.company = company;
        this.role = role;
        this.status = status;
        this.appliedDate = appliedDate;
        this.userId = userId;
        this.deadline = deadline;
        this.followUpDate = followUpDate;
    }

    public Long getId() { return id; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public LocalDate getFollowUpDate() { return followUpDate; }
    public void setFollowUpDate(LocalDate followUpDate) { this.followUpDate = followUpDate; }
}