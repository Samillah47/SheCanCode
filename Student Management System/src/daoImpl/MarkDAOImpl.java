package daoImpl;

import dao.MarkDAO;
import db.DatabaseConnection;
import model.Mark;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarkDAOImpl implements MarkDAO {

    @Override
    public void assignMark(Mark mark) {
        String sql = "INSERT INTO marks (student_id, course_id, marks) VALUES (?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, mark.getStudentId());
            ps.setInt(2, mark.getCourseId());
            ps.setDouble(3, mark.getMarks());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Mark assigned successfully!");
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error assigning mark: " + e.getMessage());
        }
    }

    @Override
    public List<Mark> getAllMarks() {
        List<Mark> marks = new ArrayList<>();

        String sql = """
                SELECT m.student_id, m.course_id, m.marks,
                       s.first_name || ' ' || s.last_name AS student_name,
                       c.course_name
                FROM marks m
                JOIN students s ON m.student_id = s.id
                JOIN courses c ON m.course_id = c.id
                ORDER BY m.student_id, m.course_id
                """;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mark mark = new Mark(
                    rs.getInt("student_id"),
                    rs.getInt("course_id"),
                    rs.getDouble("marks")
                );
                mark.setStudentName(rs.getString("student_name"));
                mark.setCourseName(rs.getString("course_name"));
                marks.add(mark);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error fetching marks: " + e.getMessage());
        }

        return marks;
    }

    @Override
    public void updateMark(Mark mark) {
        String sql = "UPDATE marks SET marks = ? WHERE student_id = ? AND course_id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, mark.getMarks());
            ps.setInt(2, mark.getStudentId());
            ps.setInt(3, mark.getCourseId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Mark updated successfully!");
            } else {
                System.out.println("Mark record not found for given student and course.");
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error updating mark: " + e.getMessage());
        }
    }

    @Override
    public void deleteMark(int studentId, int courseId) {
        String sql = "DELETE FROM marks WHERE student_id = ? AND course_id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Mark deleted successfully!");
            } else {
                System.out.println("Mark record not found for given student and course.");
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error deleting mark: " + e.getMessage());
        }
    }
}
