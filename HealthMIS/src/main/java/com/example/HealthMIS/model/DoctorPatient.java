package com.example.HealthMIS.model;

public class DoctorPatient {
    private int doctorId;
    private int patientId;
    private String doctorName;
    private String patientName;

    public DoctorPatient() {}

    public DoctorPatient(int doctorId, int patientId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
}