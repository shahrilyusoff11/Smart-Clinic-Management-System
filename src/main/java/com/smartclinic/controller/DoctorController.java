package com.smartclinic.controller;

import com.smartclinic.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<List<String>> getAvailability(
            @PathVariable Long id,
            @RequestParam(name = "date") String date) {
        
        List<String> availableTimes = doctorService.getAvailableTimeSlots(id, date);
        return ResponseEntity.ok(availableTimes);
    }

    @GetMapping
    public ResponseEntity<List<com.smartclinic.model.Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/search")
    public ResponseEntity<List<com.smartclinic.model.Doctor>> getDoctorsBySpecialtyAndTime(
            @RequestParam String specialty, 
            @RequestParam String time) {
        return ResponseEntity.ok(doctorService.getDoctorsBySpecialtyAndTime(specialty, time));
    }
}
