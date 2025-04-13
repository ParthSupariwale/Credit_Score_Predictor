package com.fintech.creditscoring.controller;

import com.fintech.creditscoring.dto.*;
import com.fintech.creditscoring.exception.ResourceNotFoundException;
import com.fintech.creditscoring.model.User;
import com.fintech.creditscoring.repository.UserRepository;
import com.fintech.creditscoring.security.JwtUtil;
import com.fintech.creditscoring.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint to request a password reset
    @PostMapping("/reset-request")
    public ResponseEntity<?> requestPasswordReset(@RequestBody PasswordResetRequest request) {
        passwordService.requestPasswordReset(request.getEmail());
        return ResponseEntity.ok("Password reset link sent to your email.");
    }

    // Endpoint to reset the password using a token
    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        passwordService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Password reset successfully.");
    }

    // Endpoint to update the password (for logged-in users)
    @PostMapping("/update")
    public ResponseEntity<?> updatePassword(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody PasswordUpdateRequest request) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        passwordService.updatePassword(user, request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok("Password updated successfully.");
    }
}