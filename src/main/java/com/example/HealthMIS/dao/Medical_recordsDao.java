package com.example.HealthMIS.dao;

import java.util.List;
import java.util.Map;

import com.example.HealthMIS.model.Medical_records;

public interface Medical_recordsDao {

    void addMedicalRecord(Medical_records record);
    List<Medical_records> getAllMedicalRecords();
    List<Medical_records> getRecordsByPatient(int patientId);
    void updateMedicalRecord(Medical_records record);
    void deleteMedicalRecord(int id);
    List<Map<String, Object>> getPatientsWithMultipleDiagnoses();
}
