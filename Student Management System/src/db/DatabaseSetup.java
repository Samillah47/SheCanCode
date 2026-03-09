package db;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {
        String createStudentsTable = """
                CREATE TABLE IF NOT EXISTS students (
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(50) NOT NULL,
                    last_name VARCHAR(50) NOT NULL,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    date_of_birth DATE NOT NULL
                );
                """;

        String createCoursesTable = """
                CREATE TABLE IF NOT EXISTS courses (
                    id SERIAL PRIMARY KEY,
                    course_name VARCHAR(100) UNIQUE NOT NULL,
                    course_description TEXT
                );
                """;

        String createMarksTable = """
                CREATE TABLE IF NOT EXISTS marks (
                    student_id INTEGER NOT NULL,
                    course_id INTEGER NOT NULL,
                    marks FLOAT NOT NULL,
                    PRIMARY KEY (student_id, course_id),
                    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
                    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
                );
                """;

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(createStudentsTable);
            System.out.println("Students table created/verified.");

            statement.execute(createCoursesTable);
            System.out.println("Courses table created/verified.");

            statement.execute(createMarksTable);
            System.out.println("Marks table created/verified.");

            statement.close();
            System.out.println("All tables are ready!\n");

        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
