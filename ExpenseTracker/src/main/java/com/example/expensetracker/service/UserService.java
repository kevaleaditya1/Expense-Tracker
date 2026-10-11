
package com.example.expensetracker.service;

import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already registered";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "Registration successful";
    }

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return "Email not registered";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Incorrect password";
        }

        return jwtService.generateToken(user.getEmail());
    }

}
