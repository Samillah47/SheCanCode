package Day11;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeManager manager = null;

        System.out.println("=== Grade Manager System ===");
        
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Grades");
            System.out.println("2. Display Grades");
            System.out.println("3. Sort Grades");
            System.out.println("4. Search Grade");
            System.out.println("5. Calculate Average");
            System.out.println("6. Get Highest Grade");
            System.out.println("7. Get Lowest Grade");
            System.out.println("8. Get Grade at Index");
            System.out.println("9. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("How many grades do you want to enter? ");
                        int count = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (manager == null) {
                            manager = new GradeManager(count);
                        }
                        
                        for (int i = 0; i < count; i++) {
                            try {
                                System.out.print("Enter grade " + (i + 1) + " (0-100): ");
                                double grade = scanner.nextDouble();
                                scanner.nextLine();
                                manager.addGrade(grade);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                                i--;
                            }
                        }
                        System.out.println("All grades added successfully!");
                        break;

                    case 2:
                        if (manager == null) {
                            System.out.println("No grades available. Please add grades first.");
                        } else {
                            manager.displayGrades();
                        }
                        break;

                    case 3:
                        if (manager == null) {
                            System.out.println("No grades available. Please add grades first.");
                        } else {
                            manager.sortGrades();
                            System.out.println("Grades sorted!");
                            manager.displayGrades();
                        }
                        break;

                    case 4:
                        if (manager == null) {
                            System.out.println("No grades available. Please add grades first.");
                        } else {
                            System.out.print("Enter grade to search: ");
                            double searchGrade = scanner.nextDouble();
                            scanner.nextLine();
                            int index = manager.searchGrade(searchGrade);
                            if (index != -1) {
                                System.out.println("Grade " + searchGrade + " found at index: " + index);
                            } else {
                                System.out.println("Grade " + searchGrade + " not found");
                            }
                        }
                        break;

                    case 5:
                        if (manager == null) {
                            System.out.println("No grades available. Please add grades first.");
                        } else {
                            System.out.println("Average grade: " + manager.calculateAverage());
                        }
                        break;

                    case 6:
                        if (manager == null) {
                            System.out.println("No grades available. Please add grades first.");
                        } else {
                            System.out.println("Highest grade: " + manager.getHighest());
                        }
                        break;

                    case 7:
                        if (manager == null) {
                            System.out.println("No grades available. Please add grades first.");
                        } else {
                            System.out.println("Lowest grade: " + manager.getLowest());
                        }
                        break;

                    case 8:
                        if (manager == null) {
                            System.out.println("No grades available. Please add grades first.");
                        } else {
                            System.out.print("Enter index: ");
                            int idx = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Grade at index " + idx + ": " + manager.getGradeAt(idx));
                        }
                        break;

                    case 9:
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Operation completed.");
            }
        }
    }
}
