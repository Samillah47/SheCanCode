package com.example.HealthMIS.dao;

import java.util.List;

import com.example.HealthMIS.model.Doctors;

public interface DoctorsDao {
    void addDoctor(Doctors doctor);
    List<Doctors> getAllDoctors();
    Doctors getDoctorById(int id);
    void updateDoctor(Doctors doctor);
    void deleteDoctor(int id);

}
