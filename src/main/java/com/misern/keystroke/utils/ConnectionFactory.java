package com.misern.keystroke.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {

    private final static String url = "jdbc:postgresql://localhost:5432/biometry";
    private final static String user = "biometry";
    private final static String password = "biometry1";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS SAMPLES (" +
                "id SERIAL PRIMARY KEY," +
                "times VARCHAR(255) NOT NULL," +
                "userName VARCHAR(50) NOT NULL" + ");"
        );

        ps.executeUpdate();
        ps.close();

        return connection;
    }
}
