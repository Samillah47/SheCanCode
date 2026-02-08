import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "students.txt";

    public static void main(String[] args) {
        StudentManager mgr = new StudentManager();
        try {
            int loaded = mgr.loadFromFile(DATA_FILE);
            System.out.println("Loaded " + loaded + " students from the file.");
        } catch (IOException ex) {
            System.out.println("Error loading data: " + ex.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            showMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    displayAll(mgr);
                    break;
                case "2":
                    displayStats(mgr);
                    break;
                case "3":
                    displayPassFail(mgr);
                    break;
                case "4":
                    addStudent(mgr, sc);
                    break;
                case "5":
                    updateScore(mgr, sc);
                    break;
                case "6":
                    saveData(mgr);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
        System.out.println("Goodbye");
    }

    private static void showMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Display all students");
        System.out.println("2. Show stats (average/top/lowest)");
        System.out.println("3. Show passed / failed");
        System.out.println("4. Add new student");
        System.out.println("5. Update student score");
        System.out.println("6. Save data to file");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private static void displayAll(StudentManager mgr) {
        System.out.println("--- Student List ---");
        for (Student s : mgr.getStudents()) System.out.println(s);
    }

    private static void displayStats(StudentManager mgr) {
        System.out.printf("Average score: %.2f\n", mgr.averageScore());
        Student top = mgr.findTopStudent();
        Student low = mgr.findLowestStudent();
        System.out.println("Top student: " + (top == null ? "None" : top.getName() + " (" + top.getScore() + ")"));
        System.out.println("Lowest student: " + (low == null ? "None" : low.getName() + " (" + low.getScore() + ")"));
    }

    private static void displayPassFail(StudentManager mgr) {
        List<Student> passed = mgr.passedStudents();
        List<Student> failed = mgr.failedStudents();
        System.out.println("Students who passed:");
        if (passed.isEmpty()) System.out.println("None"); else for (Student s : passed) System.out.println(s);
        System.out.println("Students who failed:");
        if (failed.isEmpty()) System.out.println("None"); else for (Student s : failed) System.out.println(s);
    }

    private static void addStudent(StudentManager mgr, Scanner sc) {
        try {
            System.out.print("Enter id: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter name: ");
            String name = sc.nextLine().trim();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter score: ");
            int score = Integer.parseInt(sc.nextLine().trim());
            mgr.addStudent(new Student(id, name, age, score));
            System.out.println("Student added.");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Aborting add.");
        }
    }

    private static void updateScore(StudentManager mgr, Scanner sc) {
        try {
            System.out.print("Enter student id to update: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter new score: ");
            int score = Integer.parseInt(sc.nextLine().trim());
            boolean ok = mgr.updateScore(id, score);
            System.out.println(ok ? "Score updated." : "Student not found.");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.");
        }
    }

    private static void saveData(StudentManager mgr) {
        try {
            mgr.saveToFile(DATA_FILE);
            System.out.println("Data saved successfully.");
        } catch (IOException ex) {
            System.out.println("Error saving data: " + ex.getMessage());
        }
    }
}
