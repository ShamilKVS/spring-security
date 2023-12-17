package com.example.basicAuth.controller;

import com.example.basicAuth.Enity.User;
import com.example.basicAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    final private UserRepository userRepository;

    final private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository,  BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // Encrypt the password before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }
}
