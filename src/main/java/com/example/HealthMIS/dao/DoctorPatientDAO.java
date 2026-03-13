package com.example.HealthMIS.dao;

import com.example.HealthMIS.model.DoctorPatient;
import java.util.List;
import java.util.Map;

public interface DoctorPatientDAO {
    void assignDoctorToPatient(int doctorId, int patientId);
    List<DoctorPatient> getAllRelationships();
    void removeRelationship(int doctorId, int patientId);
    List<Map<String, Object>> getDoctorsWithPatientCount();
    List<Map<String, Object>> getDoctorsWithMoreThan5Patients();
}