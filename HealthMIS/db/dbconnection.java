package com.example.HealthMIS.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class dbconnection {

    private static final String url = "jdbc:postgresql://localhost:5432/HospitalManagement";
    private static final String username = "postgres";
    private static final String password = "Saliim47";

    
    private Connection connection;

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }
}
