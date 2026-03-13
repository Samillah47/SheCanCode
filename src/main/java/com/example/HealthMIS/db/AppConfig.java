package com.example.HealthMIS.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@ComponentScan("com.example.HealthMIS")
public class AppConfig {
    
    @Bean
    @Scope("prototype")
    public Connection getConnection() throws SQLException {
        dbconnection db = new dbconnection();
        return db.getConnection();
    }
}
