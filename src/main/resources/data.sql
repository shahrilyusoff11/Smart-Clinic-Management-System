-- Insert 5 Patients
INSERT IGNORE INTO patient (id, first_name, last_name, email, phone_number, password) VALUES 
(1, 'John', 'Doe', 'john@example.com', '1112223333', 'password123'),
(2, 'Jane', 'Smith', 'jane@example.com', '2223334444', 'password123'),
(3, 'Alice', 'Johnson', 'alice@example.com', '3334445555', 'password123'),
(4, 'Bob', 'Williams', 'bob@example.com', '4445556666', 'password123'),
(5, 'Charlie', 'Brown', 'charlie@example.com', '5556667777', 'password123');

-- Insert 2 Doctors
INSERT IGNORE INTO doctor (id, first_name, last_name, email, password, specialty) VALUES 
(1, 'Gregory', 'House', 'house@clinic.com', 'docpass1', 'Diagnostician'),
(2, 'James', 'Wilson', 'wilson@clinic.com', 'docpass2', 'Oncology');

-- Insert Available times for Doctors
INSERT IGNORE INTO doctor_available_times (doctor_id, available_time) VALUES 
(1, '09:00 AM - 10:00 AM'),
(1, '10:00 AM - 11:00 AM'),
(2, '01:00 PM - 02:00 PM');

-- Insert Appointments
INSERT IGNORE INTO appointment (id, patient_id, doctor_id, appointment_time, status) VALUES 
(1, 1, 1, '2026-04-01 09:00:00', 'SCHEDULED'),
(2, 2, 1, '2026-04-01 10:00:00', 'SCHEDULED'),
(3, 3, 2, '2026-04-02 13:00:00', 'SCHEDULED');
