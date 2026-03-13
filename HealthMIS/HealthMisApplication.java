package com.example.HealthMIS;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.HealthMIS.dao.AppointmentDao;
import com.example.HealthMIS.dao.DoctorsDao;
import com.example.HealthMIS.dao.Medical_recordsDao;
import com.example.HealthMIS.dao.PatientDao;
import com.example.HealthMIS.daoImpl.AppointmentDAOImpl;
import com.example.HealthMIS.daoImpl.DoctorDAOImpl;
import com.example.HealthMIS.daoImpl.MedicalRecordDAOImpl;
import com.example.HealthMIS.daoImpl.PatientDAOImpl;
import com.example.HealthMIS.model.Appointments;
import com.example.HealthMIS.model.Doctors;
import com.example.HealthMIS.model.Medical_records;
import com.example.HealthMIS.model.Patients;

@SpringBootApplication
public class HealthMisApplication {
	private static Scanner scanner = new Scanner(System.in);
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private DoctorsDao doctorDao;
	@Autowired	
	private AppointmentDao appointmentDao;
	@Autowired
	private Medical_recordsDao medicalRecordDao;	



	public static void main(String[] args) {
		int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> doctorMenu();
                case 2 -> patientMenu();
                case 3 -> appointmentMenu();
                case 4 -> medicalRecordMenu();
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void displayMainMenu() {
        System.out.println("\n===== Health MIS Main Menu =====");
        System.out.println("1. Doctors Management");
        System.out.println("2. Patients Management");
        System.out.println("3. Appointments Management");
        System.out.println("4. Medical Records Management");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // ========== DOCTOR MENU ==========
    private static void doctorMenu() {
        int choice;
        do {
            System.out.println("\n===== Doctors Management =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Get Doctor by ID");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> viewAllDoctors();
                case 3 -> getDoctorById();
                case 4 -> updateDoctor();
                case 5 -> deleteDoctor();
                case 6 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private static void addDoctor() {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Specialty: ");
        String specialty = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        Doctors doctor = new Doctors(firstName, lastName, specialty, phone, email);
        doctorDao.addDoctor(doctor);
        System.out.println("Doctor added successfully!");
    }

    private static void viewAllDoctors() {
        List<Doctors> doctors = doctorDao.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            System.out.println("\n===== Doctors List =====");
            for (Doctors d : doctors) {
                System.out.println("ID: " + d.getId() + " | Name: " + d.getFirstName() + " " + d.getLastName() 
                    + " | Specialty: " + d.getSpecialty() + " | Phone: " + d.getPhoneNumber());
            }
        }
    }

    private static void getDoctorById() {
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();
        Doctors doctor = doctorDao.getDoctorById(id);
        if (doctor != null) {
            System.out.println("ID: " + doctor.getId() + " | Name: " + doctor.getFirstName() + " " + doctor.getLastName() 
                + " | Specialty: " + doctor.getSpecialty() + " | Phone: " + doctor.getPhoneNumber()
                + " | Email: " + doctor.getEmail());
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void updateDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Doctors doctor = doctorDao.getDoctorById(id);
        if (doctor != null) {
            System.out.print("New First Name (" + doctor.getFirstName() + "): ");
            String firstName = scanner.nextLine();
            System.out.print("New Last Name (" + doctor.getLastName() + "): ");
            String lastName = scanner.nextLine();
            System.out.print("New Specialty (" + doctor.getSpecialty() + "): ");
            String specialty = scanner.nextLine();
            System.out.print("New Phone Number (" + doctor.getPhoneNumber() + "): ");
            String phone = scanner.nextLine();
            System.out.print("New Email (" + doctor.getEmail() + "): ");
            String email = scanner.nextLine();
            
            doctor.setFirstName(firstName.isEmpty() ? doctor.getFirstName() : firstName);
            doctor.setLastName(lastName.isEmpty() ? doctor.getLastName() : lastName);
            doctor.setSpecialty(specialty.isEmpty() ? doctor.getSpecialty() : specialty);
            doctor.setPhoneNumber(phone.isEmpty() ? doctor.getPhoneNumber() : phone);
            doctor.setEmail(email.isEmpty() ? doctor.getEmail() : email);
            
            doctorDao.updateDoctor(doctor);
            System.out.println("Doctor updated successfully!");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void deleteDoctor() {
        System.out.print("Enter Doctor ID to delete: ");
        int id = scanner.nextInt();
        doctorDao.deleteDoctor(id);
        System.out.println("Doctor deleted successfully!");
    }

    // ========== PATIENT MENU ==========
    private static void patientMenu() {
        int choice;
        do {
            System.out.println("\n===== Patients Management =====");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Get Patient by ID");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPatient();
                case 2 -> viewAllPatients();
                case 3 -> getPatientById();
                case 4 -> updatePatient();
                case 5 -> deletePatient();
                case 6 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private static void addPatient() {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        Patients patient = new Patients(firstName, lastName, dob, gender, phone, email);
        patientDao.addPatient(patient);
        System.out.println("Patient added successfully!");
    }

    private static void viewAllPatients() {
        List<Patients> patients = patientDao.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("\n===== Patients List =====");
            for (Patients p : patients) {
                System.out.println("ID: " + p.getId() + " | Name: " + p.getFirstName() + " " + p.getLastName() 
                    + " | DOB: " + p.getDateOfBirth() + " | Gender: " + p.getGender() + " | Phone: " + p.getPhoneNumber());
            }
        }
    }

    private static void getPatientById() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        Patients patient = patientDao.getPatientById(id);
        if (patient != null) {
            System.out.println("ID: " + patient.getId() + " | Name: " + patient.getFirstName() + " " + patient.getLastName() 
                + " | DOB: " + patient.getDateOfBirth() + " | Gender: " + patient.getGender() 
                + " | Phone: " + patient.getPhoneNumber() + " | Email: " + patient.getEmail());
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void updatePatient() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Patients patient = patientDao.getPatientById(id);
        if (patient != null) {
            System.out.print("New First Name (" + patient.getFirstName() + "): ");
            String firstName = scanner.nextLine();
            System.out.print("New Last Name (" + patient.getLastName() + "): ");
            String lastName = scanner.nextLine();
            System.out.print("New Date of Birth (" + patient.getDateOfBirth() + "): ");
            String dob = scanner.nextLine();
            System.out.print("New Gender (" + patient.getGender() + "): ");
            String gender = scanner.nextLine();
            System.out.print("New Phone Number (" + patient.getPhoneNumber() + "): ");
            String phone = scanner.nextLine();
            System.out.print("New Email (" + patient.getEmail() + "): ");
            String email = scanner.nextLine();
            
            patient.setFirstName(firstName.isEmpty() ? patient.getFirstName() : firstName);
            patient.setLastName(lastName.isEmpty() ? patient.getLastName() : lastName);
            patient.setDateOfBirth(dob.isEmpty() ? patient.getDateOfBirth() : dob);
            patient.setGender(gender.isEmpty() ? patient.getGender() : gender);
            patient.setPhoneNumber(phone.isEmpty() ? patient.getPhoneNumber() : phone);
            patient.setEmail(email.isEmpty() ? patient.getEmail() : email);
            
            patientDao.updatePatient(patient);
            System.out.println("Patient updated successfully!");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void deletePatient() {
        System.out.print("Enter Patient ID to delete: ");
        int id = scanner.nextInt();
        patientDao.deletePatient(id);
        System.out.println("Patient deleted successfully!");
    }

    // ========== APPOINTMENT MENU ==========
    private static void appointmentMenu() {
        int choice;
        do {
            System.out.println("\n===== Appointments Management =====");
            System.out.println("1. Add Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Update Appointment Status");
            System.out.println("4. Delete Appointment");
            System.out.println("5. View Patients by Doctor");
            System.out.println("6. View Appointment Count by Doctor");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addAppointment();
                case 2 -> viewAllAppointments();
                case 3 -> updateAppointmentStatus();
                case 4 -> deleteAppointment();
                case 5 -> viewPatientsByDoctor();
                case 6 -> viewAppointmentCountByDoctor();
                case 7 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }

    private static void addAppointment() {
        System.out.print("Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Appointment Date (YYYY-MM-DD HH:MM:SS): ");
        String dateStr = scanner.nextLine();
        System.out.print("Status (Scheduled/Completed/Cancelled): ");
        String status = scanner.nextLine();
        
        try {
            Timestamp timestamp = Timestamp.valueOf(dateStr);
            Appointments appointment = new Appointments(doctorId, patientId, timestamp, status);
            appointmentDao.addAppointment(appointment);
            System.out.println("Appointment added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD HH:MM:SS");
        }
    }

    private static void viewAllAppointments() {
        List<Appointments> appointments = appointmentDao.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("\n===== Appointments List =====");
            for (Appointments a : appointments) {
                System.out.println("ID: " + a.getId() + " | Doctor ID: " + a.getDoctorId() 
                    + " | Patient ID: " + a.getPatientId() + " | Date: " + a.getAppointmentDate() 
                    + " | Status: " + a.getStatus());
            }
        }
    }

    private static void updateAppointmentStatus() {
        System.out.print("Enter Appointment ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New Status: ");
        String status = scanner.nextLine();
        appointmentDao.updateAppointmentStatus(id, status);
        System.out.println("Appointment status updated successfully!");
    }

    private static void deleteAppointment() {
        System.out.print("Enter Appointment ID to delete: ");
        int id = scanner.nextInt();
        appointmentDao.deleteAppointment(id);
        System.out.println("Appointment deleted successfully!");
    }

    private static void viewPatientsByDoctor() {
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        List<Map<String, Object>> patients = appointmentDao.getPatientsByDoctor(doctorId);
        if (patients.isEmpty()) {
            System.out.println("No patients found for this doctor.");
        } else {
            System.out.println("\n===== Patients for Doctor " + doctorId + " =====");
            for (Map<String, Object> p : patients) {
                System.out.println("Patient: " + p.get("patient_name") + " | Date: " + p.get("appointment_date"));
            }
        }
    }

    private static void viewAppointmentCountByDoctor() {
        List<Map<String, Object>> counts = appointmentDao.getAppointmentCountByDoctor();
        if (counts.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("\n===== Appointment Count by Doctor =====");
            for (Map<String, Object> c : counts) {
                System.out.println("Doctor: " + c.get("doctor_name") + " | Count: " + c.get("appointment_count"));
            }
        }
    }

    // ========== MEDICAL RECORDS MENU ==========
    private static void medicalRecordMenu() {
        int choice;
        do {
            System.out.println("\n===== Medical Records Management =====");
            System.out.println("1. Add Medical Record");
            System.out.println("2. View All Medical Records");
            System.out.println("3. View Records by Patient");
            System.out.println("4. Update Medical Record");
            System.out.println("5. Delete Medical Record");
            System.out.println("6. View Patients with Multiple Diagnoses");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMedicalRecord();
                case 2 -> viewAllMedicalRecords();
                case 3 -> viewRecordsByPatient();
                case 4 -> updateMedicalRecord();
                case 5 -> deleteMedicalRecord();
                case 6 -> viewPatientsWithMultipleDiagnoses();
                case 7 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }

    private static void addMedicalRecord() {
        System.out.print("Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Treatment: ");
        String treatment = scanner.nextLine();
        
        Medical_records record = new Medical_records(patientId, diagnosis, treatment, doctorId);
        medicalRecordDao.addMedicalRecord(record);
        System.out.println("Medical record added successfully!");
    }

    private static void viewAllMedicalRecords() {
        List<Medical_records> records = medicalRecordDao.getAllMedicalRecords();
        if (records.isEmpty()) {
            System.out.println("No medical records found.");
        } else {
            System.out.println("\n===== Medical Records List =====");
            for (Medical_records r : records) {
                System.out.println("ID: " + r.getId() + " | Patient ID: " + r.getPatientId() 
                    + " | Doctor ID: " + r.getDoctorId() + " | Diagnosis: " + r.getDiagnosis() 
                    + " | Treatment: " + r.getTreatment());
            }
        }
    }

    private static void viewRecordsByPatient() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        List<Medical_records> records = medicalRecordDao.getRecordsByPatient(patientId);
        if (records.isEmpty()) {
            System.out.println("No medical records found for this patient.");
        } else {
            System.out.println("\n===== Medical Records for Patient " + patientId + " =====");
            for (Medical_records r : records) {
                System.out.println("ID: " + r.getId() + " | Doctor ID: " + r.getDoctorId() 
                    + " | Diagnosis: " + r.getDiagnosis() + " | Treatment: " + r.getTreatment());
            }
        }
    }

    private static void updateMedicalRecord() {
        System.out.print("Enter Medical Record ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        List<Medical_records> records = medicalRecordDao.getAllMedicalRecords();
        Medical_records recordToUpdate = null;
        for (Medical_records r : records) {
            if (r.getId() == id) {
                recordToUpdate = r;
                break;
            }
        }
        
        if (recordToUpdate != null) {
            System.out.print("New Diagnosis (" + recordToUpdate.getDiagnosis() + "): ");
            String diagnosis = scanner.nextLine();
            System.out.print("New Treatment (" + recordToUpdate.getTreatment() + "): ");
            String treatment = scanner.nextLine();
            
            recordToUpdate.setDiagnosis(diagnosis.isEmpty() ? recordToUpdate.getDiagnosis() : diagnosis);
            recordToUpdate.setTreatment(treatment.isEmpty() ? recordToUpdate.getTreatment() : treatment);
            
            medicalRecordDao.updateMedicalRecord(recordToUpdate);
            System.out.println("Medical record updated successfully!");
        } else {
            System.out.println("Medical record not found.");
        }
    }

    private static void deleteMedicalRecord() {
        System.out.print("Enter Medical Record ID to delete: ");
        int id = scanner.nextInt();
        medicalRecordDao.deleteMedicalRecord(id);
        System.out.println("Medical record deleted successfully!");
    }

    private static void viewPatientsWithMultipleDiagnoses() {
        List<Map<String, Object>> patients = medicalRecordDao.getPatientsWithMultipleDiagnoses();
        if (patients.isEmpty()) {
            System.out.println("No patients with multiple diagnoses found.");
        } else {
            System.out.println("\n===== Patients with Multiple Diagnoses =====");
            for (Map<String, Object> p : patients) {
                System.out.println("Patient: " + p.get("patient_name") + " | Diagnosis Count: " + p.get("diagnosis_count"));
            }
        }
    }
}

	}

}
