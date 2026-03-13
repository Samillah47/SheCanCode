package com.example.HealthMIS.daoImpl;

import com.example.HealthMIS.dao.PatientDao;
import com.example.HealthMIS.db.dbconnection;
import com.example.HealthMIS.model.Patients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientDAOImpl implements PatientDao {
    @Autowired
    private Connection connection;

    @Override
    public void addPatient(Patients patient) {
        String sql = "INSERT INTO patients (first_name, last_name, date_of_birth, "
                   + "gender, phone_number, email) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, patient.getFirstName());
            ps.setString(2, patient.getLastName());
            ps.setDate(3, Date.valueOf(patient.getDateOfBirth()));
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getPhoneNumber());
            ps.setString(6, patient.getEmail());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Patient added successfully!");
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    @Override
    public List<Patients> getAllPatients() {
        List<Patients> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY id";
        try {
            // Connection conn = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patients patient = new Patients(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("date_of_birth"),
                    rs.getString("gender"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at")
                );
                patients.add(patient);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error fetching patients: " + e.getMessage());
        }
        return patients;
    }

    @Override
    public Patients getPatientById(int id) {
        Patients patient = null;
        String sql = "SELECT * FROM patients WHERE id = ?";
        try {
            // Connection conn = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                patient = new Patients(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("date_of_birth"),
                    rs.getString("gender"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at")
                );
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error fetching patient: " + e.getMessage());
        }
        return patient;
    }

    @Override
    public void updatePatient(Patients patient) {
        String sql = "UPDATE patients SET first_name = ?, last_name = ?, "
                   + "date_of_birth = ?, gender = ?, phone_number = ?, email = ? "
                   + "WHERE id = ?";
        try {
            // Connection conn = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, patient.getFirstName());
            ps.setString(2, patient.getLastName());
            ps.setDate(3, Date.valueOf(patient.getDateOfBirth()));
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getPhoneNumber());
            ps.setString(6, patient.getEmail());
            ps.setInt(7, patient.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Patient updated successfully!");
            else System.out.println("Patient not found with ID: " + patient.getId());
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error updating patient: " + e.getMessage());
        }
    }

    @Override
    public void deletePatient(int id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        try {
            // Connection conn = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Patient deleted successfully!");
                System.out.println("All related records also removed (cascade).");
            } else {
                System.out.println("Patient not found with ID: " + id);
            }
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        }
    }
}