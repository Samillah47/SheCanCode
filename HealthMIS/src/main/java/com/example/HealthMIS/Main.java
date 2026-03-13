package com.example.HealthMIS;

import com.example.HealthMIS.db.AppConfig;
import com.example.HealthMIS.service.HealthService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.HealthMIS.model.Doctors;
import com.example.HealthMIS.model.Patients;
import com.example.HealthMIS.model.Appointments;
import com.example.HealthMIS.model.Medical_records;
import com.example.HealthMIS.model.DoctorPatient;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HealthService healthService = context.getBean(HealthService.class);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Hospital Management Information System =====");
            System.out.println("1. Doctor Management");
            System.out.println("2. Patient Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Medical Records Management");
            System.out.println("5. Doctor-Patient Relationships");
            System.out.println("6. Reports");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> doctorMenu(scanner, healthService);
                case 2 -> patientMenu(scanner, healthService);
                case 3 -> appointmentMenu(scanner, healthService);
                case 4 -> medicalRecordMenu(scanner, healthService);
                case 5 -> doctorPatientMenu(scanner, healthService);
                case 6 -> reportsMenu(scanner, healthService);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
        
        scanner.close();
    }

    private static void doctorMenu(Scanner scanner, HealthService service) {
        int choice;
        do {
            System.out.println("\n--- Doctor Management ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Get Doctor by ID");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
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
                    service.saveDoctor(doctor);
                }
                case 2 -> {
                    List<Doctors> doctors = service.getAllDoctors();
                    doctors.forEach(d -> System.out.println(d.getId() + ": " + d.getFirstName() + " " + d.getLastName() + " - " + d.getSpecialty()));
                }
                case 3 -> {
                    System.out.print("Enter Doctor ID: ");
                    int id = scanner.nextInt();
                    Doctors doctor = service.getDoctorById(id);
                    if (doctor != null) System.out.println(doctor.getFirstName() + " " + doctor.getLastName() + " - " + doctor.getSpecialty());
                }
                case 4 -> {
                    System.out.print("Enter Doctor ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Doctors doctor = service.getDoctorById(id);
                    if (doctor != null) {
                        System.out.print("New First Name (" + doctor.getFirstName() + "): ");
                        String firstName = scanner.nextLine();
                        System.out.print("New Last Name (" + doctor.getLastName() + "): ");
                        String lastName = scanner.nextLine();
                        System.out.print("New Specialty (" + doctor.getSpecialty() + "): ");
                        String specialty = scanner.nextLine();
                        System.out.print("New Phone (" + doctor.getPhoneNumber() + "): ");
                        String phone = scanner.nextLine();
                        System.out.print("New Email (" + doctor.getEmail() + "): ");
                        String email = scanner.nextLine();
                        doctor.setFirstName(firstName.isEmpty() ? doctor.getFirstName() : firstName);
                        doctor.setLastName(lastName.isEmpty() ? doctor.getLastName() : lastName);
                        doctor.setSpecialty(specialty.isEmpty() ? doctor.getSpecialty() : specialty);
                        doctor.setPhoneNumber(phone.isEmpty() ? doctor.getPhoneNumber() : phone);
                        doctor.setEmail(email.isEmpty() ? doctor.getEmail() : email);
                        service.updateDoctor(doctor);
                    }
                }
                case 5 -> {
                    System.out.print("Enter Doctor ID: ");
                    int id = scanner.nextInt();
                    service.deleteDoctor(id);
                }
            }
        } while (choice != 0);
    }

    private static void patientMenu(Scanner scanner, HealthService service) {
        int choice;
        do {
            System.out.println("\n--- Patient Management ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Get Patient by ID");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
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
                    service.savePatient(patient);
                }
                case 2 -> {
                    List<Patients> patients = service.getAllPatients();
                    patients.forEach(p -> System.out.println(p.getId() + ": " + p.getFirstName() + " " + p.getLastName()));
                }
                case 3 -> {
                    System.out.print("Enter Patient ID: ");
                    int id = scanner.nextInt();
                    Patients patient = service.getPatientById(id);
                    if (patient != null) System.out.println(patient.getFirstName() + " " + patient.getLastName());
                }
                case 4 -> {
                    System.out.print("Enter Patient ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Patients patient = service.getPatientById(id);
                    if (patient != null) {
                        System.out.print("New First Name (" + patient.getFirstName() + "): ");
                        String firstName = scanner.nextLine();
                        System.out.print("New Last Name (" + patient.getLastName() + "): ");
                        String lastName = scanner.nextLine();
                        patient.setFirstName(firstName.isEmpty() ? patient.getFirstName() : firstName);
                        patient.setLastName(lastName.isEmpty() ? patient.getLastName() : lastName);
                        service.updatePatient(patient);
                    }
                }
                case 5 -> {
                    System.out.print("Enter Patient ID: ");
                    int id = scanner.nextInt();
                    service.deletePatient(id);
                }
            }
        } while (choice != 0);
    }

    private static void appointmentMenu(Scanner scanner, HealthService service) {
        int choice;
        do {
            System.out.println("\n--- Appointment Management ---");
            System.out.println("1. Add Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Update Appointment Status");
            System.out.println("4. Delete Appointment");
            System.out.println("5. View Patients by Doctor");
            System.out.println("6. View Appointment Count by Doctor");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    System.out.print("Patient ID: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Appointment Date (YYYY-MM-DD HH:MM:SS): ");
                    String dateStr = scanner.nextLine();
                    Timestamp timestamp = Timestamp.valueOf(dateStr);
                    System.out.print("Status: ");
                    String status = scanner.nextLine();
                    Appointments appointment = new Appointments(doctorId, patientId, timestamp, status);
                    service.saveAppointment(appointment);
                }
                case 2 -> {
                    List<Appointments> appointments = service.getAllAppointments();
                    appointments.forEach(a -> System.out.println(a.getId() + ": Doc-" + a.getDoctorId() + " Pat-" + a.getPatientId() + " " + a.getAppointmentDate()));
                }
                case 3 -> {
                    System.out.print("Appointment ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New Status: ");
                    String status = scanner.nextLine();
                    service.updateAppointmentStatus(id, status);
                }
                case 4 -> {
                    System.out.print("Appointment ID: ");
                    int id = scanner.nextInt();
                    service.deleteAppointment(id);
                }
                case 5 -> {
                    System.out.print("Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    List<Map<String, Object>> patients = service.getPatientsByDoctor(doctorId);
                    patients.forEach(p -> System.out.println(p.get("patient_name") + " - " + p.get("appointment_date")));
                }
                case 6 -> {
                    List<Map<String, Object>> counts = service.getAppointmentCountByDoctor();
                    counts.forEach(c -> System.out.println(c.get("doctor_name") + ": " + c.get("total_appointments")));
                }
            }
        } while (choice != 0);
    }

    private static void medicalRecordMenu(Scanner scanner, HealthService service) {
        int choice;
        do {
            System.out.println("\n--- Medical Records Management ---");
            System.out.println("1. Add Medical Record");
            System.out.println("2. View All Medical Records");
            System.out.println("3. View Records by Patient");
            System.out.println("4. Update Medical Record");
            System.out.println("5. Delete Medical Record");
            System.out.println("6. Patients with Multiple Diagnoses");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
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
                    service.saveMedicalRecord(record);
                }
                case 2 -> {
                    List<Medical_records> records = service.getAllMedicalRecords();
                    records.forEach(r -> System.out.println(r.getId() + ": Patient-" + r.getPatientId() + " " + r.getDiagnosis()));
                }
                case 3 -> {
                    System.out.print("Patient ID: ");
                    int patientId = scanner.nextInt();
                    List<Medical_records> records = service.getRecordsByPatient(patientId);
                    records.forEach(r -> System.out.println(r.getDiagnosis() + " - " + r.getTreatment()));
                }
                case 4 -> {
                    System.out.print("Record ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    List<Medical_records> records = service.getAllMedicalRecords();
                    Medical_records recordToUpdate = records.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
                    if (recordToUpdate != null) {
                        System.out.print("New Diagnosis: ");
                        String diagnosis = scanner.nextLine();
                        System.out.print("New Treatment: ");
                        String treatment = scanner.nextLine();
                        recordToUpdate.setDiagnosis(diagnosis);
                        recordToUpdate.setTreatment(treatment);
                        service.updateMedicalRecord(recordToUpdate);
                    }
                }
                case 5 -> {
                    System.out.print("Record ID: ");
                    int id = scanner.nextInt();
                    service.deleteMedicalRecord(id);
                }
                case 6 -> {
                    List<Map<String, Object>> patients = service.getPatientsWithMultipleDiagnoses();
                    patients.forEach(p -> System.out.println(p.get("patient_name") + ": " + p.get("diagnosis_count")));
                }
            }
        } while (choice != 0);
    }

    private static void doctorPatientMenu(Scanner scanner, HealthService service) {
        int choice;
        do {
            System.out.println("\n--- Doctor-Patient Relationships ---");
            System.out.println("1. Assign Doctor to Patient");
            System.out.println("2. View All Relationships");
            System.out.println("3. Remove Relationship");
            System.out.println("4. Doctors with Patient Count");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    System.out.print("Patient ID: ");
                    int patientId = scanner.nextInt();
                    service.saveDoctorPatient(new DoctorPatient(doctorId, patientId));
                }
                case 2 -> {
                    List<DoctorPatient> relationships = service.getAllDoctorPatients();
                    relationships.forEach(r -> System.out.println("Doctor-" + r.getDoctorId() + " <-> Patient-" + r.getPatientId()));
                }
                case 3 -> {
                    System.out.print("Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    System.out.print("Patient ID: ");
                    int patientId = scanner.nextInt();
                    service.removeDoctorPatient(doctorId, patientId);
                }
                case 4 -> {
                    List<Map<String, Object>> doctors = service.getDoctorsWithPatientCount();
                    doctors.forEach(d -> System.out.println(d.get("doctor_name") + ": " + d.get("patient_count")));
                }
            }
        } while (choice != 0);
    }

    private static void reportsMenu(Scanner scanner, HealthService service) {
        int choice;
        do {
            System.out.println("\n--- Reports ---");
            System.out.println("1. Appointments per Month");
            System.out.println("2. Doctors with More Than 5 Patients");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    List<Map<String, Object>> appointments = service.getAppointmentsPerMonth();
                    appointments.forEach(a -> System.out.println(a.get("month") + ": " + a.get("total_appointments")));
                }
                case 2 -> {
                    List<Map<String, Object>> doctors = service.getDoctorsWithMoreThan5Patients();
                    doctors.forEach(d -> System.out.println(d.get("doctor_name") + ": " + d.get("patient_count")));
                }
            }
        } while (choice != 0);
    }
}
