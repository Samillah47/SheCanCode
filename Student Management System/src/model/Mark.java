package model;

public class Mark {
    private int studentId;
    private int courseId;
    private double marks;

    private String studentName;
    private String courseName;

    public Mark() {}

    public Mark(int studentId, int courseId, double marks) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    @Override
    public String toString() {
        return "| Student ID: " + studentId + " | " + studentName
               + " | Course ID: " + courseId + " | " + courseName
               + " | Marks: " + marks + " |";
    }
}
