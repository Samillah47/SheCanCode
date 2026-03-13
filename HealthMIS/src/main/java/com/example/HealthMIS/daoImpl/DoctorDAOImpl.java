package com.example.HealthMIS.daoImpl;

import com.example.HealthMIS.dao.DoctorsDao;
import com.example.HealthMIS.db.dbconnection;
import com.example.HealthMIS.model.Doctors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorDAOImpl implements DoctorsDao {

    // dbconnection db = new dbconnection();
    @Autowired
    private Connection connection;

    @Override
    public void addDoctor(Doctors doctor) {
        String sql = "INSERT INTO doctors (first_name, last_name, specialty, "
                   + "phone_number, email) VALUES (?, ?, ?, ?, ?)";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getSpecialty());
            ps.setString(4, doctor.getPhoneNumber());
            ps.setString(5, doctor.getEmail());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Doctor added successfully!");
            ps.close();
        } catch (Exception e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }

    @Override
    public List<Doctors> getAllDoctors() {
        List<Doctors> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors ORDER BY id";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctors doctor = new Doctors(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("specialty"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at")
                );
                doctors.add(doctor);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error fetching doctors: " + e.getMessage());
        }
        return doctors;
    }

    @Override
    public Doctors getDoctorById(int id) {
        Doctors doctor = null;
        String sql = "SELECT * FROM doctors WHERE id = ?";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doctor = new Doctors(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("specialty"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at")
                );
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error fetching doctor: " + e.getMessage());
        }
        return doctor;
    }

    @Override
    public void updateDoctor(Doctors doctor) {
        String sql = "UPDATE doctors SET first_name = ?, last_name = ?, "
                   + "specialty = ?, phone_number = ?, email = ? WHERE id = ?";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getSpecialty());
            ps.setString(4, doctor.getPhoneNumber());
            ps.setString(5, doctor.getEmail());
            ps.setInt(6, doctor.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Doctor updated successfully!");
            else System.out.println("Doctor not found with ID: " + doctor.getId());
            ps.close();
        } catch (Exception e) {
            System.out.println("Error updating doctor: " + e.getMessage());
        }
    }

    @Override
    public void deleteDoctor(int id) {
        String sql = "DELETE FROM doctors WHERE id = ?";
        try {
            // Connection conn = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Doctor deleted successfully!");
            else System.out.println("Doctor not found with ID: " + id);
            ps.close();
        } catch (Exception e) {
            System.out.println("Error deleting doctor: " + e.getMessage());
        }
    }
}