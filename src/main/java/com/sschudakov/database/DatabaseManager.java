package com.sschudakov.database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Semen Chudakov on 10.12.2017.
 */
public class DatabaseManager {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_NAME = "underground";


    public static final String USER_PARAMETER = "user=";
    public static final String USER = "user1";
    public static final String PASSWORD_PARAMETER = "password=";
    public static final String PASSWORD = "pass1";
    public static final String USE_SSL_PARAMETER = "useSSL=";
    public static final String USE_SSL = "false";


    public static final String DATABASE_URL = "jdbc:mysql://localhost/";
    public static final String DATABASE_FULL_URL = DATABASE_URL + DATABASE_NAME + "?" +
            USER_PARAMETER + USER + "&" +
            PASSWORD_PARAMETER + PASSWORD + "&" +
            USE_SSL_PARAMETER + USE_SSL;
    public static final String DATABASE_CREATION_SQL_STATEMENT = "CREATE DATABASE ";


    public static ConnectionSource connectionSource;


    static {
        try {
            connectionSource = new JdbcConnectionSource(DATABASE_FULL_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        Collection<Class<?>> daoClasses = DaoClassesReader.readClasses();
        for (Class<?> daoClass : daoClasses) {
            System.out.println(daoClass);
        }
        for (Class<?> daoClass : daoClasses) {
            try {
                TableManager.createTable(daoClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dropTables() {
        Collection<Class<?>> daoClasses = DaoClassesReader.readClasses();
        for (Class<?> daoClass : daoClasses) {
            try {
                TableManager.dropTable(daoClass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
