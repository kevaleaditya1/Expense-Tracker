
package com.example.expensetracker.controller;

import com.example.expensetracker.model.LoginRequest;
import com.example.expensetracker.model.User;
import com.example.expensetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        String result = userService.register(user);

        if (result.equals("Email already registered")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String token = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        if (token.equals("Email not registered")
                || token.equals("Incorrect password")) {
            return ResponseEntity.status(401).body(token);
        }

        return ResponseEntity.ok(
                java.util.Map.of(
                        "message", "Login successful",
                        "token", token
                )
        );
    }


}
