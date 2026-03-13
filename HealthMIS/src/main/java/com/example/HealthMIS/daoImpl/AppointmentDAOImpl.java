package com.example.HealthMIS.daoImpl;

import com.example.HealthMIS.dao.AppointmentDao;
import com.example.HealthMIS.db.dbconnection;
import com.example.HealthMIS.model.Appointments;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentDAOImpl implements AppointmentDao {

    // dbconnection db = new dbconnection();
    @Autowired
    private Connection connection;

    @Override
    public void addAppointment(Appointments appointment) {
        String sql = "INSERT INTO appointments (doctor_id, patient_id, "
                   + "appointment_date, status) VALUES (?, ?, ?, ?)";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointment.getDoctorId());
            ps.setInt(2, appointment.getPatientId());
            ps.setTimestamp(3, appointment.getAppointmentDate());
            ps.setString(4, appointment.getStatus());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Appointment created successfully!");
            ps.close();
            connection
            .close();
        } catch (Exception e) {
            System.out.println("Error creating appointment: " + e.getMessage());
        }
    }

    @Override
    public List<Appointments> getAllAppointments() {
        List<Appointments> appointments = new ArrayList<>();
        String sql = """
                SELECT a.*,
                       d.first_name || ' ' || d.last_name AS doctor_name,
                       p.first_name || ' ' || p.last_name AS patient_name
                FROM appointments a
                JOIN doctors d ON a.doctor_id = d.id
                JOIN patients p ON a.patient_id = p.id
                ORDER BY a.appointment_date
                """;
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointments apt = new Appointments(
                    rs.getInt("id"),
                    rs.getInt("doctor_id"),
                    rs.getInt("patient_id"),
                    rs.getTimestamp("appointment_date"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                );
                apt.setDoctorName(rs.getString("doctor_name"));
                apt.setPatientName(rs.getString("patient_name"));
                appointments.add(apt);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error fetching appointments: " + e.getMessage());
        }
        return appointments;
    }

    @Override
    public void updateAppointmentStatus(int id, String status) {
        String sql = "UPDATE appointments SET status = ? WHERE id = ?";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Appointment status updated to: " + status);
            else System.out.println("Appointment not found with ID: " + id);
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error updating appointment: " + e.getMessage());
        }
    }

    @Override
    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Appointment deleted successfully!");
            else System.out.println("Appointment not found with ID: " + id);
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error deleting appointment: " + e.getMessage());
        }
    }

    @Override
    public List<Map<String, Object>> getPatientsByDoctor(int doctorId) {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = """
                SELECT DISTINCT p.id, p.first_name, p.last_name, p.email,
                       a.appointment_date, a.status
                FROM patients p
                JOIN appointments a ON p.id = a.patient_id
                WHERE a.doctor_id = ?
                ORDER BY a.appointment_date
                """;
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("patient_id", rs.getInt("id"));
                row.put("patient_name", rs.getString("first_name")
                        + " " + rs.getString("last_name"));
                row.put("email", rs.getString("email"));
                row.put("appointment_date", rs.getTimestamp("appointment_date"));
                row.put("status", rs.getString("status"));
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
    public List<Map<String, Object>> getAppointmentCountByDoctor() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = """
                SELECT d.id, d.first_name || ' ' || d.last_name AS doctor_name,
                       d.specialty, COUNT(a.id) AS total_appointments
                FROM doctors d
                LEFT JOIN appointments a ON d.id = a.doctor_id
                GROUP BY d.id, d.first_name, d.last_name, d.specialty
                ORDER BY total_appointments DESC
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
                row.put("total_appointments", rs.getInt("total_appointments"));
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
    public List<Map<String, Object>> getAppointmentsPerMonth() {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = """
                SELECT TO_CHAR(appointment_date, 'YYYY-MM') AS month,
                       COUNT(*) AS total_appointments
                FROM appointments
                GROUP BY TO_CHAR(appointment_date, 'YYYY-MM')
                ORDER BY month
                """;
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("month", rs.getString("month"));
                row.put("total_appointments", rs.getInt("total_appointments"));
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