package com.smartclinic.controller;

import com.smartclinic.dto.AuthResponse;
import com.smartclinic.dto.LoginRequest;
import com.smartclinic.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final DoctorService doctorService;
    private final com.smartclinic.security.TokenService tokenService;

    public AuthController(DoctorService doctorService, com.smartclinic.security.TokenService tokenService) {
        this.doctorService = doctorService;
        this.tokenService = tokenService;
    }

    @PostMapping("/doctor/login")
    public ResponseEntity<AuthResponse> doctorLogin(@RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse response = doctorService.validateLoginCredentials(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(null);
        }
    }
    
    @PostMapping("/patient/login")
    public ResponseEntity<AuthResponse> patientLogin(@RequestBody LoginRequest loginRequest) {
        // Return real JWT for curl testing
        String token = tokenService.generateToken(loginRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, "ROLE_PATIENT"));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<AuthResponse> adminLogin(@RequestBody LoginRequest loginRequest) {
        // Return real JWT for curl testing
        String token = tokenService.generateToken(loginRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, "ROLE_ADMIN"));
    }
}
