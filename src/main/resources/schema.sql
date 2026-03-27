DROP PROCEDURE IF EXISTS GetDailyAppointmentReportByDoctor;
DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByMonth;
DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByYear;

CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN doc_id BIGINT, IN report_date DATE)
SELECT a.id, p.first_name, p.last_name, a.appointment_time, a.status
FROM appointment a
JOIN patient p ON a.patient_id = p.id
WHERE a.doctor_id = doc_id AND DATE(a.appointment_time) = report_date;

CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(IN target_year INT, IN target_month INT)
SELECT d.id, d.first_name, d.last_name, COUNT(DISTINCT a.patient_id) as patient_count
FROM doctor d
JOIN appointment a ON d.id = a.doctor_id
WHERE YEAR(a.appointment_time) = target_year AND MONTH(a.appointment_time) = target_month
GROUP BY d.id, d.first_name, d.last_name
ORDER BY patient_count DESC
LIMIT 1;

CREATE PROCEDURE GetDoctorWithMostPatientsByYear(IN target_year INT)
SELECT d.id, d.first_name, d.last_name, COUNT(DISTINCT a.patient_id) as patient_count
FROM doctor d
JOIN appointment a ON d.id = a.doctor_id
WHERE YEAR(a.appointment_time) = target_year
GROUP BY d.id, d.first_name, d.last_name
ORDER BY patient_count DESC
LIMIT 1;
