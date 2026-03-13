package com.example.HealthMIS.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class setup {
    
    @EventListener(ApplicationReadyEvent.class)
    public void createTables(){
        String doctors = """
                CREATE TABLE IF NOT EXISTS doctors(
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(50),
                last_name VARCHAR(50),
                specialty VARCHAR(100),
                phone_number VARCHAR(20),
                email VARCHAR(100) UNIQUE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

            String patients = """
                CREATE TABLE IF NOT EXISTS patients(
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(50),
                last_name VARCHAR(50),
                date_of_birth DATE,
                gender VARCHAR(10),
                phone_number VARCHAR(20),
                email VARCHAR(100) UNIQUE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

            String appointments = """
                CREATE TABLE IF NOT EXISTS appointments(
                id SERIAL PRIMARY KEY,
                doctor_id INT,
                patient_id INT,
                appointment_date TIMESTAMP,
                status VARCHAR(50),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY(doctor_id) REFERENCES doctors(id) ON DELETE CASCADE,
                FOREIGN KEY(patient_id) REFERENCES patients(id) ON DELETE CASCADE
                )
                """;

            String medical_records = """
                CREATE TABLE IF NOT EXISTS medical_records(
                id SERIAL PRIMARY KEY,
                patient_id INT,
                doctor_id INT,
                diagnosis TEXT,
                treatment TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY(patient_id) REFERENCES patients(id) ON DELETE CASCADE,
                FOREIGN KEY(doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
                )
                """;

            String doctor_patient = """
                CREATE TABLE IF NOT EXISTS doctor_patient(
                doctor_id INT,
                patient_id INT,
                PRIMARY KEY(doctor_id, patient_id),
                FOREIGN KEY(doctor_id) REFERENCES doctors(id) ON DELETE CASCADE,
                FOREIGN KEY(patient_id) REFERENCES patients(id) ON DELETE CASCADE
                )
                """;

        try (Connection conn = new dbconnection().getConnection()) {
            if (conn != null) {
                conn.createStatement().execute(doctors);
                conn.createStatement().execute(patients);
                conn.createStatement().execute(appointments);
                conn.createStatement().execute(medical_records);
                conn.createStatement().execute(doctor_patient);
                System.out.println("Tables created successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }

    }  
    
}
