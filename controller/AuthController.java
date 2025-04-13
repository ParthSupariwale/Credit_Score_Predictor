package com.fintech.creditscoring.controller;

import com.fintech.creditscoring.exception.DuplicateResourceException;
import com.fintech.creditscoring.model.User; // Import User entity
import com.fintech.creditscoring.repository.UserRepository; // Import UserRepository
import com.fintech.creditscoring.security.*;

import jakarta.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder; // Import PasswordEncoder
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        // Check if the email is already registered
        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Error: Email is already in use!");
        }

        // Create a new user
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setAge(signupRequest.getAge()); // Set the age

        // Save the user to the database
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}