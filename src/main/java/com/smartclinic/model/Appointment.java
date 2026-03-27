package com.smartclinic.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @NotNull(message = "Appointment time is required")
    @FutureOrPresent(message = "Appointment time must be in the present or future")
    @Column(nullable = false)
    private LocalDateTime appointmentTime;
    
    @Column(nullable = false)
    private String status; // e.g., "SCHEDULED", "COMPLETED"
}
