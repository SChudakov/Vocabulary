package com.sschudakov.desktop.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Semen Chudakov on 10.12.2017.
 */
public class DatabaseManager {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_NAME = "vocabulary";


    private static final String USER_PARAMETER = "user=";
    private static final String USER = "admin";
    private static final String PASSWORD_PARAMETER = "password=";
    private static final String PASSWORD = "admin";
    private static final String USE_SSL_PARAMETER = "useSSL=";
    private static final String USE_SSL = "false";


    private static final String DATABASE_URL = "jdbc:mysql://localhost/";
    private static final String DATABASE_FULL_URL = DATABASE_URL + DATABASE_NAME + "?" +
            USER_PARAMETER + USER + "&" +
            PASSWORD_PARAMETER + PASSWORD + "&" +
            USE_SSL_PARAMETER + USE_SSL;

    public static Connection connection;


    static {
        try {
            connection = DriverManager.getConnection(DATABASE_FULL_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
