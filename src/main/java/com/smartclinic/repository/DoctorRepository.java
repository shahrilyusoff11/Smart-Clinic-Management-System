package com.smartclinic.repository;

import com.smartclinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findById(Long id);
    
    @org.springframework.data.jpa.repository.Query("SELECT d FROM Doctor d JOIN d.availableTimes t WHERE d.specialty = :specialty AND t = :time")
    java.util.List<Doctor> findBySpecialtyAndAvailableTime(String specialty, String time);
}
