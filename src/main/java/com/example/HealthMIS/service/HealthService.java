package com.example.HealthMIS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealthMIS.dao.DoctorsDao;
import com.example.HealthMIS.dao.PatientDao;
import com.example.HealthMIS.dao.AppointmentDao;
import com.example.HealthMIS.dao.Medical_recordsDao;
import com.example.HealthMIS.dao.DoctorPatientDAO;

import com.example.HealthMIS.model.Doctors;
import com.example.HealthMIS.model.Patients;
import com.example.HealthMIS.model.Appointments;
import com.example.HealthMIS.model.Medical_records;
import com.example.HealthMIS.model.DoctorPatient;

import java.util.List;
import java.util.Map;

@Service
public class HealthService {
    
    @Autowired
    private DoctorsDao doctorsDao;
    
    @Autowired
    private PatientDao patientDao;
    
    @Autowired
    private AppointmentDao appointmentDao;
    
    @Autowired
    private Medical_recordsDao medicalRecordsDao;
    
    @Autowired
    private DoctorPatientDAO doctorPatientDAO;
    
    // Doctor operations
    public void saveDoctor(Doctors doctor) {
        doctorsDao.addDoctor(doctor);
    }
    
    public List<Doctors> getAllDoctors() {
        return doctorsDao.getAllDoctors();
    }
    
    public Doctors getDoctorById(int id) {
        return doctorsDao.getDoctorById(id);
    }
    
    public void updateDoctor(Doctors doctor) {
        doctorsDao.updateDoctor(doctor);
    }
    
    public void deleteDoctor(int id) {
        doctorsDao.deleteDoctor(id);
    }
    
    // Patient operations
    public void savePatient(Patients patient) {
        patientDao.addPatient(patient);
    }
    
    public List<Patients> getAllPatients() {
        return patientDao.getAllPatients();
    }
    
    public Patients getPatientById(int id) {
        return patientDao.getPatientById(id);
    }
    
    public void updatePatient(Patients patient) {
        patientDao.updatePatient(patient);
    }
    
    public void deletePatient(int id) {
        patientDao.deletePatient(id);
    }
    
    // Appointment operations
    public void saveAppointment(Appointments appointment) {
        appointmentDao.addAppointment(appointment);
    }
    
    public List<Appointments> getAllAppointments() {
        return appointmentDao.getAllAppointments();
    }
    
    public void updateAppointmentStatus(int id, String status) {
        appointmentDao.updateAppointmentStatus(id, status);
    }
    
    public void deleteAppointment(int id) {
        appointmentDao.deleteAppointment(id);
    }
    
    public List<Map<String, Object>> getPatientsByDoctor(int doctorId) {
        return appointmentDao.getPatientsByDoctor(doctorId);
    }
    
    public List<Map<String, Object>> getAppointmentCountByDoctor() {
        return appointmentDao.getAppointmentCountByDoctor();
    }
    
    // Medical Records operations
    public void saveMedicalRecord(Medical_records record) {
        medicalRecordsDao.addMedicalRecord(record);
    }
    
    public List<Medical_records> getAllMedicalRecords() {
        return medicalRecordsDao.getAllMedicalRecords();
    }
    
    public List<Medical_records> getRecordsByPatient(int patientId) {
        return medicalRecordsDao.getRecordsByPatient(patientId);
    }
    
    public void updateMedicalRecord(Medical_records record) {
        medicalRecordsDao.updateMedicalRecord(record);
    }
    
    public void deleteMedicalRecord(int id) {
        medicalRecordsDao.deleteMedicalRecord(id);
    }
    
    public List<Map<String, Object>> getPatientsWithMultipleDiagnoses() {
        return medicalRecordsDao.getPatientsWithMultipleDiagnoses();
    }
    
    // Doctor-Patient relationship operations
    public void saveDoctorPatient(DoctorPatient doctorPatient) {
        doctorPatientDAO.assignDoctorToPatient(doctorPatient.getDoctorId(), doctorPatient.getPatientId());
    }
    
    public List<DoctorPatient> getAllDoctorPatients() {
        return doctorPatientDAO.getAllRelationships();
    }
    
    public void removeDoctorPatient(int doctorId, int patientId) {
        doctorPatientDAO.removeRelationship(doctorId, patientId);
    }
    
    public List<Map<String, Object>> getDoctorsWithPatientCount() {
        return doctorPatientDAO.getDoctorsWithPatientCount();
    }
    
    public List<Map<String, Object>> getDoctorsWithMoreThan5Patients() {
        return doctorPatientDAO.getDoctorsWithMoreThan5Patients();
    }
    
    public List<Map<String, Object>> getAppointmentsPerMonth() {
        return appointmentDao.getAppointmentsPerMonth();
    }
}
