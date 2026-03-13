package com.example.HealthMIS.daoImpl;

import com.example.HealthMIS.dao.DoctorPatientDAO;
import com.example.HealthMIS.db.dbconnection;
import com.example.HealthMIS.model.DoctorPatient;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorPatientDAOImpl implements DoctorPatientDAO {

    // dbconnection db = new dbconnection();
    @Autowired
    private Connection connection;

    @Override
    public void assignDoctorToPatient(int doctorId, int patientId) {
        String sql = "INSERT INTO doctor_patient (doctor_id, patient_id) VALUES (?, ?)";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, doctorId);
            ps.setInt(2, patientId);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Doctor assigned to patient successfully!");
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<DoctorPatient> getAllRelationships() {
        List<DoctorPatient> list = new ArrayList<>();
        String sql = """
                SELECT dp.*,
                       d.first_name || ' ' || d.last_name AS doctor_name,
                       p.first_name || ' ' || p.last_name AS patient_name
                FROM doctor_patient dp
                JOIN doctors d ON dp.doctor_id = d.id
                JOIN patients p ON dp.patient_id = p.id
                ORDER BY dp.doctor_id, dp.patient_id
                """;
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DoctorPatient dp = new DoctorPatient(
                    rs.getInt("doctor_id"),
                    rs.getInt("patient_id")
                );
                dp.setDoctorName(rs.getString("doctor_name"));
                dp.setPatientName(rs.getString("patient_name"));
                list.add(dp);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void removeRelationship(int doctorId, int patientId) {
        String sql = "DELETE FROM doctor_patient WHERE doctor_id = ? AND patient_id = ?";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, doctorId);
            ps.setInt(2, patientId);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Relationship removed successfully!");
            else System.out.println("Relationship not found.");
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> getDoctorsWithPatientCount() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = """
                SELECT d.id, d.first_name || ' ' || d.last_name AS doctor_name,
                       d.specialty, COUNT(dp.patient_id) AS patient_count
                FROM doctors d
                LEFT JOIN doctor_patient dp ON d.id = dp.doctor_id
                GROUP BY d.id, d.first_name, d.last_name, d.specialty
                ORDER BY patient_count DESC
                """;
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("doctor_id", rs.getInt("id"));
                row.put("doctor_name", rs.getString("doctor_name"));
                row.put("specialty", rs.getString("specialty"));
                row.put("patient_count", rs.getInt("patient_count"));
                results.add(row);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return results;
    }

    @Override
    public List<Map<String, Object>> getDoctorsWithMoreThan5Patients() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = """
                SELECT d.id, d.first_name || ' ' || d.last_name AS doctor_name,
                       d.specialty, COUNT(dp.patient_id) AS patient_count
                FROM doctors d
                JOIN doctor_patient dp ON d.id = dp.doctor_id
                GROUP BY d.id, d.first_name, d.last_name, d.specialty
                HAVING COUNT(dp.patient_id) > 5
                ORDER BY patient_count DESC
                """;
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("doctor_id", rs.getInt("id"));
                row.put("doctor_name", rs.getString("doctor_name"));
                row.put("specialty", rs.getString("specialty"));
                row.put("patient_count", rs.getInt("patient_count"));
                results.add(row);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return results;
    }
}