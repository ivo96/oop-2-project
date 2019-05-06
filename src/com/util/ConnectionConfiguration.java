package com.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class ConnectionConfiguration {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://172.17.0.3:3306/test", "sa", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
