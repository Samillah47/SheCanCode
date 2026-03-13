import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Map<Integer, String> students = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== STUDENT MANAGEMENT SYSTEM ===");
        
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Sort Students");
            System.out.println("5. Find Student with Highest ID");
            System.out.println("6. Find Student with Lowest ID");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    try {
                        manager.addStudent(students, id, name);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter student ID to search: ");
                    int searchId = scanner.nextInt();
                    manager.searchStudent(students, searchId);
                    break;
                    
                case 3:
                    System.out.print("Enter student ID to remove: ");
                    int removeId = scanner.nextInt();
                    manager.removeStudent(students, removeId);
                    break;
                    
                case 4:
                    manager.sortStudents(students);
                    break;
                    
                case 5:
                    manager.findStudentWithHighestId(students);
                    break;
                    
                case 6:
                    manager.findStudentWithLowestId(students);
                    break;
                    
                case 7:
                    System.out.println("Thank you");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
