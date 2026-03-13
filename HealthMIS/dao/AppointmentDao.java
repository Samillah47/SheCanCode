package com.example.HealthMIS.dao;

import java.util.List;
import java.util.Map;

import com.example.HealthMIS.model.Appointments;

public interface AppointmentDao {
    void addAppointment(Appointments appointment);
    List<Appointments> getAllAppointments();
    void updateAppointmentStatus(int id, String status);
    void deleteAppointment(int id);
    List<Map<String, Object>> getPatientsByDoctor(int doctorId);
    List<Map<String, Object>> getAppointmentCountByDoctor();
    List<Map<String, Object>> getAppointmentsPerMonth();

}
