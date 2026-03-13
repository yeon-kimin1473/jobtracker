package com.example.back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.back.model.User;
import com.example.back.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register
    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists";
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "User registered successfully";
    }

    // Login
    public Map<String, Object> loginUser(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            response.put("message", "User not found");
            return response;
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            response.put("message", "Invalid password");
            return response;
        }
        response.put("message", "Login successful");
        response.put("userId", user.get().getId());
        response.put("username", user.get().getUsername());
        return response;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}