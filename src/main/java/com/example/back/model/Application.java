package com.example.back.model;

import jakarta.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String jobTitle;
    private String location;
    private String status;
    private String appliedDate;
    private String notes;

    public Application(){}

    public Long getId(){ return id; }

    public String getCompanyName(){ return companyName; }
    public void setCompanyName(String companyName){ this.companyName = companyName; }

    public String getJobTitle(){ return jobTitle; }
    public void setJobTitle(String jobTitle){ this.jobTitle = jobTitle; }

    public String getLocation(){ return location; }
    public void setLocation(String location){ this.location = location; }

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }

    public String getAppliedDate(){ return appliedDate; }
    public void setAppliedDate(String appliedDate){ this.appliedDate = appliedDate; }

    public String getNotes(){ return notes; }
    public void setNotes(String notes){ this.notes = notes; }
}