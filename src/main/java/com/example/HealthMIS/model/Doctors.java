package com.example.HealthMIS.model;

import java.sql.Timestamp;

public class Doctors {
    private int id;
    private String firstName;
    private String lastName;
    private String specialty;
    private String phoneNumber;
    private String email;
    private Timestamp createdAt;

    public Doctors() {}

    public Doctors(String firstName, String lastName, String specialty,
                  String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Doctors(int id, String firstName, String lastName, String specialty,
                  String phoneNumber, String email, Timestamp createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
