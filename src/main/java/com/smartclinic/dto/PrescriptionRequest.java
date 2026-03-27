package com.smartclinic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrescriptionRequest {
    @NotNull(message = "Appointment ID is required")
    private Long appointmentId;
    
    @NotBlank(message = "Medication name is required")
    private String medicationName;
    
    @NotBlank(message = "Dosage is required")
    private String dosage;
    
    private String notes;
}
