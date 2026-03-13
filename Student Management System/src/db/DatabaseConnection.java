package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/student_management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Saliim47";

    private static Connection connection = null;

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");

            } catch (ClassNotFoundException e) {
                System.out.println("PostgreSQL Driver not found!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
