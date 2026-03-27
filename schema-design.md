# MySQL Schema Design

## Tables

### 1. `patient`
| Column Name   | Data Type    | Constraints                  | Description                          |
|---------------|--------------|------------------------------|--------------------------------------|
| id            | BIGINT       | PRIMARY KEY, AUTO_INCREMENT  | Unique identifier for the patient.   |
| first_name    | VARCHAR(255) | NOT NULL                     | Patient's first name.                |
| last_name     | VARCHAR(255) | NOT NULL                     | Patient's last name.                 |
| email         | VARCHAR(255) | UNIQUE, NOT NULL             | Email address for login/contact.     |
| phone_number  | VARCHAR(20)  | UNIQUE, NOT NULL             | Contact phone number.                |
| password      | VARCHAR(255) | NOT NULL                     | Encrypted password.                  |
| role          | VARCHAR(50)  | NOT NULL                     | Role for authentication ('ROLE_PATIENT') |

### 2. `doctor`
| Column Name   | Data Type    | Constraints                  | Description                          |
|---------------|--------------|------------------------------|--------------------------------------|
| id            | BIGINT       | PRIMARY KEY, AUTO_INCREMENT  | Unique identifier for the doctor.    |
| email         | VARCHAR(255) | UNIQUE, NOT NULL             | Email for login.                     |
| password      | VARCHAR(255) | NOT NULL                     | Encrypted password.                  |
| first_name    | VARCHAR(255) | NOT NULL                     | Doctor's first name.                 |
| last_name     | VARCHAR(255) | NOT NULL                     | Doctor's last name.                  |
| specialty     | VARCHAR(255) | NULL                         | Medical specialty.                   |
| role          | VARCHAR(50)  | NOT NULL                     | Role for auth ('ROLE_DOCTOR')        |

#### `doctor_available_times` (Element Collection table created by Hibernate)
| Column Name   | Data Type    | Constraints                  | Description                          |
|---------------|--------------|------------------------------|--------------------------------------|
| doctor_id     | BIGINT       | FOREIGN KEY references doctor| The doctor this time belongs to.     |
| available_times| VARCHAR(255)| NOT NULL                     | Time slot (e.g., '10:00 AM - 11:00 AM')|

### 3. `appointment`
| Column Name      | Data Type    | Constraints                  | Description                          |
|------------------|--------------|------------------------------|--------------------------------------|
| id               | BIGINT       | PRIMARY KEY, AUTO_INCREMENT  | Unique identifier for appointment.   |
| patient_id       | BIGINT       | FOREIGN KEY ref patient      | The patient booking the appt.        |
| doctor_id        | BIGINT       | FOREIGN KEY ref doctor       | The doctor for the appt.             |
| appointment_time | DATETIME     | NOT NULL                     | The date and time of the appointment.|
| status           | VARCHAR(50)  | NOT NULL                     | Status ('SCHEDULED', 'COMPLETED')    |

### 4. `prescription`
| Column Name    | Data Type    | Constraints                   | Description                          |
|----------------|--------------|-------------------------------|--------------------------------------|
| id             | BIGINT       | PRIMARY KEY, AUTO_INCREMENT   | Unique identifier for prescription.  |
| appointment_id | BIGINT       | FOREIGN KEY ref appointment   | The appointment this belongs to.     |
| medication_name| VARCHAR(255) | NOT NULL                      | Name of the prescribed medication.   |
| dosage         | VARCHAR(255) | NOT NULL                      | Dosage instructions.                 |
| notes          | TEXT         | NULL                          | Additional doctor notes.             |

## System Users (for Admin)
Admin users might just be tracked via a separate table or through a unified User table. For simplicity, Admin roles and Doctor/Patient will authenticate via the same or simple tables or static Admin config.

## Foreign Key Relationships
- `appointment.patient_id` -> `patient.id`
- `appointment.doctor_id` -> `doctor.id`
- `prescription.appointment_id` -> `appointment.id`
- `doctor_available_times.doctor_id` -> `doctor.id`
