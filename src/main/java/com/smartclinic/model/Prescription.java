package com.smartclinic.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
    
    @Column(nullable = false)
    private String medicationName;
    
    @Column(nullable = false)
    private String dosage;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
}
