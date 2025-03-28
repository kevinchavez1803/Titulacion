package com.gymapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/BD_GYM";
    private static final String USER = "postgres"; // Cambiar por tu usuario de la base de datos
    private static final String PASSWORD = "29021803"; // Cambiar por tu contraseña de la base de datos

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC de PostgreSQL no encontrado", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}