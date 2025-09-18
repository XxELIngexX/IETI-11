package com.example.IETI_11.auth;


import com.example.IETI_11.model.User;
import com.example.IETI_11.model.UserDto;
import com.example.IETI_11.repository.UserRepository;
import com.example.IETI_11.security.JwtUtil;
import com.example.IETI_11.service.UsersService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    private final UsersService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,UsersService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // ==== DTOs ====
    public record LoginRequest(String email,
                               String password) {}

    public record LoginResponse(String token) {}

    public record RegisterRequest(String name,
                                  String lastName,
                                  String phoneNumber,
                                  String password,
                                  String email) {}


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login( @RequestBody LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );
        String token = jwtUtil.generate(req.email());
        return ResponseEntity.ok(new LoginResponse(token));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody RegisterRequest user) {
        if (userService.findByEmail(user.email).isEmpty()) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }
        User newUser = userService.create(
                new User(
                    new UserDto(
                            user.name(),
                            user.lastName(),
                            user.email(),
                            user.phoneNumber(),
                            passwordEncoder.encode(user.password())
                    )
                )
        );


        return ResponseEntity.ok(newUser);
    }
}

