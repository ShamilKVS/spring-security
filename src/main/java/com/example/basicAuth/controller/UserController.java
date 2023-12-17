package com.example.basicAuth.controller;

import com.example.basicAuth.Enity.User;
import com.example.basicAuth.repository.UserRepository;
import com.example.basicAuth.security.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    final private UserRepository userRepository;
    final private UserAuthenticationProvider userAuthenticationProvider;

    final private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, UserAuthenticationProvider userAuthenticationProvider, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userAuthenticationProvider = userAuthenticationProvider;
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
    public String showLoginForm(@RequestBody User user) {

        Authentication authentication1 = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authentication = this.userAuthenticationProvider.authenticate(authentication1);

        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }
}
