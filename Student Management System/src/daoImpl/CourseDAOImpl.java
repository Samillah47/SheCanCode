package daoImpl;

import dao.CourseDAO;
import db.DatabaseConnection;
import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseDescription());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Course added successfully!");
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY id";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("id"),
                    rs.getString("course_name"),
                    rs.getString("course_description")
                );
                courses.add(course);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error fetching courses: " + e.getMessage());
        }

        return courses;
    }

    @Override
    public Course getCourseById(int id) {
        Course course = null;
        String sql = "SELECT * FROM courses WHERE id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                course = new Course(
                    rs.getInt("id"),
                    rs.getString("course_name"),
                    rs.getString("course_description")
                );
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error fetching course: " + e.getMessage());
        }

        return course;
    }

    @Override
    public void updateCourse(Course course) {
        String sql = "UPDATE courses SET course_name = ?, course_description = ? WHERE id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseDescription());
            ps.setInt(3, course.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Course updated successfully!");
            } else {
                System.out.println("Course with ID " + course.getId() + " not found.");
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    @Override
    public void deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Course deleted successfully!");
            } else {
                System.out.println("Course with ID " + id + " not found.");
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }
}
