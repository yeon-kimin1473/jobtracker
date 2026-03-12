package com.example.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}