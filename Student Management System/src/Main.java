import dao.StudentDAO;
import dao.CourseDAO;
import dao.MarkDAO;
import daoImpl.StudentDAOImpl;
import daoImpl.CourseDAOImpl;
import daoImpl.MarkDAOImpl;
import db.DatabaseConnection;
import model.Course;
import model.Mark;
import model.Student;
import db.DatabaseSetup;

import java.util.List;
import java.util.Scanner;

public class Main {

    static StudentDAO studentDAO = new StudentDAOImpl();
    static CourseDAO courseDAO = new CourseDAOImpl();
    static MarkDAO markDAO = new MarkDAOImpl();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=== Setting up Database ===");
        DatabaseSetup.createTables();

        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> courseMenu();
                case 3 -> markMenu();
                case 0 -> {
                    running = false;
                    DatabaseConnection.closeConnection();
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice! Try again.\n");
            }
        }

        scanner.close();
    }

    static void printMainMenu() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   STUDENT MANAGEMENT SYSTEM          ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║   1. Manage Students                 ║");
        System.out.println("║   2. Manage Courses                  ║");
        System.out.println("║   3. Manage Marks                    ║");
        System.out.println("║   0. Exit                            ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    static void studentMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> viewStudentById();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine().trim();

        Student student = new Student(firstName, lastName, email, dob);
        studentDAO.addStudent(student);
    }

    static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("-----------------------------------------------------------");
            System.out.printf("| %-4s | %-12s | %-12s | %-20s | %-12s |%n",
                    "ID", "First Name", "Last Name", "Email", "DOB");
            System.out.println("-----------------------------------------------------------");

            for (Student s : students) {
                System.out.printf("| %-4d | %-12s | %-12s | %-20s | %-12s |%n",
                        s.getId(), s.getFirstName(), s.getLastName(),
                        s.getEmail(), s.getDateOfBirth());
            }
            System.out.println("-----------------------------------------------------------");
        }
    }

    static void viewStudentById() {
        int id = getIntInput("Enter Student ID: ");
        Student student = studentDAO.getStudentById(id);

        if (student != null) {
            System.out.println("\nStudent Found:");
            System.out.println("ID         : " + student.getId());
            System.out.println("First Name : " + student.getFirstName());
            System.out.println("Last Name  : " + student.getLastName());
            System.out.println("Email      : " + student.getEmail());
            System.out.println("DOB        : " + student.getDateOfBirth());
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    static void updateStudent() {
        int id = getIntInput("Enter Student ID to update: ");

        Student existing = studentDAO.getStudentById(id);
        if (existing == null) {
            System.out.println("Student not found with ID: " + id);
            return;
        }

        System.out.println("Current details: " + existing);
        System.out.println("Enter new details (press Enter to keep current value):");

        System.out.print("First Name [" + existing.getFirstName() + "]: ");
        String firstName = scanner.nextLine().trim();
        if (firstName.isEmpty()) firstName = existing.getFirstName();

        System.out.print("Last Name [" + existing.getLastName() + "]: ");
        String lastName = scanner.nextLine().trim();
        if (lastName.isEmpty()) lastName = existing.getLastName();

        System.out.print("Email [" + existing.getEmail() + "]: ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) email = existing.getEmail();

        System.out.print("DOB [" + existing.getDateOfBirth() + "]: ");
        String dob = scanner.nextLine().trim();
        if (dob.isEmpty()) dob = existing.getDateOfBirth();

        Student updated = new Student(id, firstName, lastName, email, dob);
        studentDAO.updateStudent(updated);
    }

    static void deleteStudent() {
        int id = getIntInput("Enter Student ID to delete: ");
        studentDAO.deleteStudent(id);
    }

    static void courseMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Delete Course");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1 -> addCourse();
                case 2 -> viewAllCourses();
                case 3 -> updateCourse();
                case 4 -> deleteCourse();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addCourse() {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Course Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Course Description: ");
        String description = scanner.nextLine().trim();

        Course course = new Course(name, description);
        courseDAO.addCourse(course);
    }

    static void viewAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = courseDAO.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("------------------------------------------------------");
            System.out.printf("| %-4s | %-20s | %-25s |%n",
                    "ID", "Course Name", "Description");
            System.out.println("------------------------------------------------------");

            for (Course c : courses) {
                System.out.printf("| %-4d | %-20s | %-25s |%n",
                        c.getId(), c.getCourseName(), c.getCourseDescription());
            }
            System.out.println("------------------------------------------------------");
        }
    }

    static void updateCourse() {
        int id = getIntInput("Enter Course ID to update: ");

        Course existing = courseDAO.getCourseById(id);
        if (existing == null) {
            System.out.println("Course not found with ID: " + id);
            return;
        }

        System.out.println("Current details: " + existing);

        System.out.print("Course Name [" + existing.getCourseName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = existing.getCourseName();

        System.out.print("Description [" + existing.getCourseDescription() + "]: ");
        String desc = scanner.nextLine().trim();
        if (desc.isEmpty()) desc = existing.getCourseDescription();

        Course updated = new Course(id, name, desc);
        courseDAO.updateCourse(updated);
    }

    static void deleteCourse() {
        int id = getIntInput("Enter Course ID to delete: ");
        courseDAO.deleteCourse(id);
    }

    static void markMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Marks Management ---");
            System.out.println("1. Assign Mark");
            System.out.println("2. View All Marks");
            System.out.println("3. Update Mark");
            System.out.println("4. Delete Mark");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1 -> assignMark();
                case 2 -> viewAllMarks();
                case 3 -> updateMark();
                case 4 -> deleteMark();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void assignMark() {
        System.out.println("\n--- Assign Mark ---");
        int studentId = getIntInput("Student ID: ");
        int courseId = getIntInput("Course ID: ");
        double marks = getDoubleInput("Marks: ");

        Mark mark = new Mark(studentId, courseId, marks);
        markDAO.assignMark(mark);
    }

    static void viewAllMarks() {
        System.out.println("\n--- All Marks ---");
        List<Mark> marks = markDAO.getAllMarks();

        if (marks.isEmpty()) {
            System.out.println("No marks found.");
        } else {
            System.out.println("-------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-18s | %-9s | %-15s | %-6s |%n",
                    "Std ID", "Student Name", "Crs ID", "Course Name", "Marks");
            System.out.println("-------------------------------------------------------------------------");

            for (Mark m : marks) {
                System.out.printf("| %-10d | %-18s | %-9d | %-15s | %-6.1f |%n",
                        m.getStudentId(), m.getStudentName(),
                        m.getCourseId(), m.getCourseName(), m.getMarks());
            }
            System.out.println("-------------------------------------------------------------------------");
        }
    }

    static void updateMark() {
        System.out.println("\n--- Update Mark ---");
        int studentId = getIntInput("Student ID: ");
        int courseId = getIntInput("Course ID: ");
        double marks = getDoubleInput("New Marks: ");

        Mark mark = new Mark(studentId, courseId, marks);
        markDAO.updateMark(mark);
    }

    static void deleteMark() {
        System.out.println("\n--- Delete Mark ---");
        int studentId = getIntInput("Student ID: ");
        int courseId = getIntInput("Course ID: ");

        markDAO.deleteMark(studentId, courseId);
    }

    static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}
