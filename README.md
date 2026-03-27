# Smart Clinic Management System

## Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8.0+
- Docker (optional)

## Quick Start with Docker (Recommended)
You can instantly run the entire system (both the Application and MySQL Database) using Docker.

1. Open your terminal in the project folder.
2. Run this single command:
   ```bash
   docker-compose up -d --build
   ```
3. The UI will be available at `http://localhost:8080/index.html`.

## Database Setup (Local)
If you prefer running without Docker, open MySQL and create a database called `smart_clinic`:

```sql
CREATE DATABASE IF NOT EXISTS smart_clinic;
```

The application is configured to connect to MySQL on `localhost:3306` with username `root` and password `root`. Update `src/main/resources/application.properties` if your credentials differ.

## Running Locally

Compile and package the application:

```bash
mvn clean package -DskipTests
```

Run the Spring Boot app:

```bash
java -jar target/smart-clinic-management-system-0.0.1-SNAPSHOT.jar
```

## Application Structure
- **Frontend Assets**: Admin, Doctor, and Patient portals available at `http://localhost:8080/index.html`. For UI screenshots, try logging in directly from the frontend without needing credentials!
- **Backend APIs**: Secured via stateless JWT authentication.
- **Stored Procedures**: Three MySQL stored procedures are initialized automatically using `schema.sql` when Spring Boot starts up.
- **Test Data**: The `data.sql` file automatically seeds 5 patients, 2 doctors, and active appointments into the database on startup.

## Testing the API (Curl Commands)

To retrieve data via the API (like for your grading questions), you need a valid JWT token. Use the built-in test users injected by `data.sql`!

**1. Doctor Login (e.g., Dr. House):**
```bash
curl -X POST http://localhost:8080/api/auth/doctor/login \
     -H "Content-Type: application/json" \
     -d '{"email":"house@clinic.com", "password":"docpass1"}'
```

**2. Patient Login (e.g., John Doe):**
```bash
curl -X POST http://localhost:8080/api/auth/patient/login \
     -H "Content-Type: application/json" \
     -d '{"email":"john@example.com", "password":"password123"}'
```

**3. Retrieve All Patient Appointments (Question 25):**
Replace `YOUR_TOKEN_HERE` with the token generated from the Patient Login request.
```bash
curl -X GET http://localhost:8080/api/appointments/patient \
     -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

**4. Retrieve All Doctors (Question 24):**
```bash
curl -X GET http://localhost:8080/api/doctors
```

**5. Search Doctors by Specialty & Time (Question 26):**
```bash
curl -X GET "http://localhost:8080/api/doctors/search?specialty=Diagnostician&time=10:00%20AM%20-%2011:00%20AM"
```