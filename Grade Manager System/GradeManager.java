package Day11;

import java.util.Arrays;

public class GradeManager {
    private double[] grades;
    private int count;

    public GradeManager(int capacity) {
        grades = new double[capacity];
        count = 0;
    }

    public void addGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100. Received: " + grade);
        }
        if (count >= grades.length) {
            throw new IllegalArgumentException("Grade list is full. Cannot add more grades.");
        }
        grades[count++] = grade;
    }

    public void sortGrades() {
        if (count == 0) {
            throw new IllegalArgumentException("No grades to sort.");
        }
        Arrays.sort(grades, 0, count);
    }

    public int searchGrade(double grade) {
        for (int i = 0; i < count; i++) {
            if (grades[i] == grade) {
                return i;
            }
        }
        return -1;
    }

    public double calculateAverage() {
        if (count == 0) {
            throw new IllegalArgumentException("No grades available to calculate average.");
        }
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += grades[i];
        }
        return sum / count;
    }

    public double getHighest() {
        if (count == 0) {
            throw new IllegalArgumentException("No grades available.");
        }
        double highest = grades[0];
        for (int i = 1; i < count; i++) {
            if (grades[i] > highest) {
                highest = grades[i];
            }
        }
        return highest;
    }

    public double getLowest() {
        if (count == 0) {
            throw new IllegalArgumentException("No grades available.");
        }
        double lowest = grades[0];
        for (int i = 1; i < count; i++) {
            if (grades[i] < lowest) {
                lowest = grades[i];
            }
        }
        return lowest;
    }

    public double getGradeAt(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds. Valid range: 0-" + (count - 1));
        }
        return grades[index];
    }

    public void displayGrades() {
        if (count == 0) {
            System.out.println("No grades to display.");
            return;
        }
        System.out.print("Grades: [");
        for (int i = 0; i < count; i++) {
            System.out.print(grades[i]);
            if (i < count - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public int getCount() {
        return count;
    }
}
