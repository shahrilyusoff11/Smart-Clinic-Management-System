package com.smartclinic.service;

import com.smartclinic.dto.AuthResponse;
import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import com.smartclinic.security.TokenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final TokenService tokenService;

    public DoctorService(DoctorRepository doctorRepository, TokenService tokenService) {
        this.doctorRepository = doctorRepository;
        this.tokenService = tokenService;
    }

    public List<String> getAvailableTimeSlots(Long doctorId, String date) {
        // Return available times from Doctor entity logic for demonstration.
        // A complete app would check existing appointments for conflicts on the specific date.
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isPresent()) {
            return doctorOpt.get().getAvailableTimes();
        }
        return List.of();
    }

    public AuthResponse validateLoginCredentials(String email, String password) {
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            // Matching raw passwords for demonstration, in production use BCrypt.
            if (doctor.getPassword().equals(password)) {
                String token = tokenService.generateToken(email);
                return new AuthResponse(token, "ROLE_DOCTOR");
            }
        }
        throw new RuntimeException("Invalid credentials");
    }
}
