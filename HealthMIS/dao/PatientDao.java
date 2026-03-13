package com.example.HealthMIS.dao;

import java.util.List;

import com.example.HealthMIS.model.Patients;

public interface PatientDao {
    void addPatient(Patients patient);
    List<Patients> getAllPatients();
    Patients getPatientById(int id);
    void updatePatient(Patients patient);
    void deletePatient(int id);

}
