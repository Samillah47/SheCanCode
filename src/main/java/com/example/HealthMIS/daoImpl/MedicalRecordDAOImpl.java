package com.example.HealthMIS.daoImpl;

import com.example.HealthMIS.dao.Medical_recordsDao;
import com.example.HealthMIS.db.dbconnection;
import com.example.HealthMIS.model.Medical_records;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MedicalRecordDAOImpl implements Medical_recordsDao {

    @Autowired
    private Connection connection;

    @Override
    public void addMedicalRecord(Medical_records record) {
        String sql = "INSERT INTO medical_records (patient_id, diagnosis, "
                   + "treatment, doctor_id) VALUES (?, ?, ?, ?)";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, record.getPatientId());
            ps.setString(2, record.getDiagnosis());
            ps.setString(3, record.getTreatment());
            ps.setInt(4, record.getDoctorId());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Medical record added successfully!");
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error adding medical record: " + e.getMessage());
        }
    }

    @Override
    public List<Medical_records> getAllMedicalRecords() {
        List<Medical_records> records = new ArrayList<>();
        String sql = """
                SELECT mr.*,
                       p.first_name || ' ' || p.last_name AS patient_name,
                       d.first_name || ' ' || d.last_name AS doctor_name
                FROM medical_records mr
                JOIN patients p ON mr.patient_id = p.id
                JOIN doctors d ON mr.doctor_id = d.id
                ORDER BY mr.id
                """;
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medical_records record = new Medical_records(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    rs.getString("diagnosis"),
                    rs.getString("treatment"),
                    rs.getInt("doctor_id"),
                    rs.getTimestamp("created_at")
                );
                record.setPatientName(rs.getString("patient_name"));
                record.setDoctorName(rs.getString("doctor_name"));
                records.add(record);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error fetching records: " + e.getMessage());
        }
        return records;
    }

    @Override
    public List<Medical_records> getRecordsByPatient(int patientId) {
        List<Medical_records> records = new ArrayList<>();
        String sql = """
                SELECT mr.*,
                       p.first_name || ' ' || p.last_name AS patient_name,
                       d.first_name || ' ' || d.last_name AS doctor_name
                FROM medical_records mr
                JOIN patients p ON mr.patient_id = p.id
                JOIN doctors d ON mr.doctor_id = d.id
                WHERE mr.patient_id = ?
                ORDER BY mr.created_at
                """;
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medical_records record = new Medical_records(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    rs.getString("diagnosis"),
                    rs.getString("treatment"),
                    rs.getInt("doctor_id"),
                    rs.getTimestamp("created_at")
                );
                record.setPatientName(rs.getString("patient_name"));
                record.setDoctorName(rs.getString("doctor_name"));
                records.add(record);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return records;
    }

    @Override
    public void updateMedicalRecord(Medical_records record) {
        String sql = "UPDATE medical_records SET diagnosis = ?, treatment = ?, "
                   + "doctor_id = ? WHERE id = ?";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, record.getDiagnosis());
            ps.setString(2, record.getTreatment());
            ps.setInt(3, record.getDoctorId());
            ps.setInt(4, record.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Medical record updated successfully!");
            else System.out.println("Record not found with ID: " + record.getId());
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }

    @Override
    public void deleteMedicalRecord(int id) {
        String sql = "DELETE FROM medical_records WHERE id = ?";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Medical record deleted successfully!");
            else System.out.println("Record not found with ID: " + id);
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> getPatientsWithMultipleDiagnoses() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = """
                SELECT p.id, p.first_name || ' ' || p.last_name AS patient_name,
                       COUNT(mr.id) AS diagnosis_count
                FROM patients p
                JOIN medical_records mr ON p.id = mr.patient_id
                GROUP BY p.id, p.first_name, p.last_name
                HAVING COUNT(mr.id) > 1
                ORDER BY diagnosis_count DESC
                """;
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("patient_id", rs.getInt("id"));
                row.put("patient_name", rs.getString("patient_name"));
                row.put("diagnosis_count", rs.getInt("diagnosis_count"));
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