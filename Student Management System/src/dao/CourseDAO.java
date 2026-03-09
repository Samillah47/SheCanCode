package dao;

import model.Course;
import java.util.List;

public interface CourseDAO {
    void addCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(int id);
    void updateCourse(Course course);
    void deleteCourse(int id);
}
