package com.example.testservlets.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection {

    private static String url = "jdbc:postgresql://localhost:5432/vovadb";
    private static String user = "vova";
    private static String password = "Pa329789123";



    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
