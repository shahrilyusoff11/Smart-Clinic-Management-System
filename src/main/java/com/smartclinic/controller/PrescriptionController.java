package com.smartclinic.controller;

import com.smartclinic.dto.PrescriptionRequest;
import com.smartclinic.model.Appointment;
import com.smartclinic.model.Prescription;
import com.smartclinic.repository.AppointmentRepository;
import com.smartclinic.repository.PrescriptionRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;

    public PrescriptionController(PrescriptionRepository prescriptionRepository, AppointmentRepository appointmentRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping
    public ResponseEntity<?> savePrescription(@Valid @RequestBody PrescriptionRequest request) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(request.getAppointmentId());
        if (appointmentOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Appointment not found");
        }
        
        Prescription prescription = new Prescription();
        prescription.setAppointment(appointmentOpt.get());
        prescription.setMedicationName(request.getMedicationName());
        prescription.setDosage(request.getDosage());
        prescription.setNotes(request.getNotes());
        
        Prescription saved = prescriptionRepository.save(prescription);
        return ResponseEntity.ok(saved);
    }
}
