package com.example.back.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public JobApplication() {}

    public JobApplication(String company, String role, String status, LocalDate appliedDate, Long userId) {
        this.company = company;
        this.role = role;
        this.status = status;
        this.appliedDate = appliedDate;
        this.userId = userId;
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
}