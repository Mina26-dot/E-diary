package com.iktpreobuka.elektronskidnevnik1.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSqlConnection {
	public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/elektronski_dnevnik1";
        String username = "SpringUser";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}

