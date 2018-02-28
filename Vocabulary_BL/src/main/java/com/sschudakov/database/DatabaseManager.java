package com.sschudakov.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Semen Chudakov on 10.12.2017.
 */
public class DatabaseManager {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_NAME = "vocabulary";


    public static final String USER_PARAMETER = "user=";
    public static final String USER = "admin";
    public static final String PASSWORD_PARAMETER = "password=";
    public static final String PASSWORD = "admin";
    public static final String USE_SSL_PARAMETER = "useSSL=";
    public static final String USE_SSL = "false";


    public static final String DATABASE_URL = "jdbc:mysql://localhost/";
    public static final String DATABASE_FULL_URL = DATABASE_URL + DATABASE_NAME + "?" +
            USER_PARAMETER + USER + "&" +
            PASSWORD_PARAMETER + PASSWORD + "&" +
            USE_SSL_PARAMETER + USE_SSL;
    public static final String DATABASE_CREATION_SQL_STATEMENT = "CREATE DATABASE ";

    public static Connection connection;


    static {
        try {
            connection = DriverManager.getConnection(DATABASE_FULL_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
