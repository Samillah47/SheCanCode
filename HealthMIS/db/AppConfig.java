package com.example.HealthMIS.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.HealthMIS.dao.DoctorsDao;
import com.example.HealthMIS.dao.PatientDao;
import com.example.HealthMIS.dao.AppointmentDao;
import com.example.HealthMIS.dao.Medical_recordsDao;
import com.example.HealthMIS.dao.DoctorPatientDAO;

import com.example.HealthMIS.daoImpl.DoctorDAOImpl;
import com.example.HealthMIS.daoImpl.PatientDAOImpl;
import com.example.HealthMIS.daoImpl.AppointmentDAOImpl;
import com.example.HealthMIS.daoImpl.MedicalRecordDAOImpl;
import com.example.HealthMIS.daoImpl.DoctorPatientDAOImpl;

@Configuration
@ComponentScan("com.example.HealthMIS")
public class AppConfig {
    
    @Bean
    public DoctorsDao doctorsDao() {
        return new DoctorDAOImpl();
    }
    
    @Bean
    public PatientDao patientDao() {
        return new PatientDAOImpl();
    }
    
    @Bean
    public AppointmentDao appointmentDao() {
        return new AppointmentDAOImpl();
    }
    
    @Bean
    public Medical_recordsDao medicalRecordsDao() {
        return new MedicalRecordDAOImpl();
    }
    
    @Bean
    public DoctorPatientDAO doctorPatientDAO() {
        return new DoctorPatientDAOImpl();
    }
    
}
