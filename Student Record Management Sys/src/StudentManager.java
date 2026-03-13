import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() { return students; }

    public int loadFromFile(String path) throws IOException {
        students.clear();
        File file = new File(path);
        if (!file.exists()) throw new FileNotFoundException("File not found: " + path);

        int loaded = 0;
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            boolean any = false;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                any = true;
                String[] parts = line.split(",");
                if (parts.length != 4) continue;
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    int score = Integer.parseInt(parts[3].trim());
                    students.add(new Student(id, name, age, score));
                    loaded++;
                } catch (NumberFormatException ex) {
                    // skip invalid line
                }
            }
            if (!any) throw new IOException("File is empty");
        }
        return loaded;
    }

    public void saveToFile(String path) throws IOException {
        try (PrintWriter w = new PrintWriter(new FileWriter(path))) {
            for (Student s : students) {
                w.println(s.getId() + ", " + s.getName() + ", " + s.getAge() + ", " + s.getScore());
            }
        }
    }

    public void addStudent(Student s) { students.add(s); }

    public boolean updateScore(int studentId, int newScore) {
        for (Student s : students) {
            if (s.getId() == studentId) {
                s.setScore(newScore);
                return true;
            }
        }
        return false;
    }

    public Student findTopStudent() {
        return students.stream().max(Comparator.comparingInt(Student::getScore)).orElse(null);
    }

    public Student findLowestStudent() {
        return students.stream().min(Comparator.comparingInt(Student::getScore)).orElse(null);
    }

    public double averageScore() {
        return students.stream().mapToInt(Student::getScore).average().orElse(0.0);
    }

    public List<Student> passedStudents() {
        List<Student> out = new ArrayList<>();
        for (Student s : students) if (s.getScore() >= 60) out.add(s);
        return out;
    }

    public List<Student> failedStudents() {
        List<Student> out = new ArrayList<>();
        for (Student s : students) if (s.getScore() < 60) out.add(s);
        return out;
    }
}
