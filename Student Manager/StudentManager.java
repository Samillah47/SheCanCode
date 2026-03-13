import java.util.*;

public class StudentManager {
    
    public void addStudent(Map<Integer, String> students, int studentId, String name) {
        try {
            if (studentId <= 0) {
                throw new IllegalArgumentException("Invalid input: Student ID cannot be negative, and name cannot be empty!");
            }
            
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid input: Student ID cannot be negative, and name cannot be empty!");
            }
            
            students.put(studentId, name);
            System.out.println("Student added successfully.");
            System.out.println("HashMap: " + students);
            
        } catch (NullPointerException e) {
            System.out.println("Error: Map cannot be null!");
        }
    }
    
    public Map<Integer, String> sortStudents(Map<Integer, String> students) {
        try {
            TreeMap<Integer, String> sortedStudents = new TreeMap<>(students);
            System.out.println("Sorted students: " + sortedStudents);
            return sortedStudents;
            
        } catch (NullPointerException e) {
            System.out.println("Error: Cannot sort null map!");
            return new TreeMap<>();
        }
    }
    
    public void searchStudent(Map<Integer, String> students, int studentId) {
        try {
            if (students.containsKey(studentId)) {
                String name = students.get(studentId);
                System.out.println("Student with ID " + studentId + ": " + name);
            } else {
                System.out.println("Student not found with ID: " + studentId);
            }
            
        } catch (NullPointerException e) {
            System.out.println("Error: Map cannot be null!");
        }
    }
    
    public void removeStudent(Map<Integer, String> students, int studentId) {
        try {
            if (students.containsKey(studentId)) {
                students.remove(studentId);
                System.out.println("HashMap after removing student ID " + studentId + ": " + students);
            } else {
                System.out.println("Cannot remove: Student not found with ID: " + studentId);
            }
            
        } catch (NullPointerException e) {
            System.out.println("Error: Map cannot be null!");
        }
    }
    
    public void findStudentWithHighestId(Map<Integer, String> students) {
        try {
            if (students.isEmpty()) {
                System.out.println("No students in the map!");
                return;
            }
            
            int maxId = Collections.max(students.keySet());
            System.out.println("Student with highest ID: " + students.get(maxId));
            
        } catch (NullPointerException e) {
            System.out.println("Error: Map cannot be null!");
        }
    }
    
    public void findStudentWithLowestId(Map<Integer, String> students) {
        try {
            if (students.isEmpty()) {
                System.out.println("No students in the map!");
                return;
            }
            
            int minId = Collections.min(students.keySet());
            System.out.println("Student with lowest ID: " + students.get(minId));
            
        } catch (NullPointerException e) {
            System.out.println("Error: Map cannot be null!");
        } finally {
            System.out.println("Search operation completed.");
        }
    }
}
