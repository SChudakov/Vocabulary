package com.sschudakov.database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Semen Chudakov on 10.12.2017.
 */
public class DatabaseManager {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_NAME = "vocabulary";

    private static final String USER = "user1";
    private static final String PASSWORD = "pass1";

    private static final String DATABASE_URL = "jdbc:mysql://localhost/";
    private static final String DATABASE_FULL_URL = DATABASE_URL + DATABASE_NAME + "?" +
            "user=" + USER +
            "&password=" + PASSWORD;
    private static final String DATABASE_CREATION_SQL_STATEMENT = "CREATE DATABASE ";


    private static ConnectionSource connectionSource;


    static {
        try {
            connectionSource = new JdbcConnectionSource(
                    DATABASE_URL + DATABASE_NAME,
                    USER,
                    PASSWORD
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //getters
    public static String getJdbcDriver() {
        return JDBC_DRIVER;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }

    public static String getDatabaseFullUrl() {
        return DATABASE_FULL_URL;
    }

    public static String getDatabaseCreationSqlStatement() {
        return DATABASE_CREATION_SQL_STATEMENT;
    }

    public static ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public static void createDatabase() {
//        DatabaseCreator.createDatabase(DATABASE_NAME.toUpperCase());
    }

    public static void createTables() {
//        WordCollection<Class<?>> daoClasses = DaosClassesReader.readClasses();
//        for (Class<?> daoClass : daoClasses) {
//            try {
//                TablesManager.createTable(daoClass);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void dropTables() {
//        WordCollection<Class<?>> daoClasses = DaosClassesReader.readClasses();
//        for (Class<?> daoClass : daoClasses) {
//            try {
//                TablesManager.dropTable(daoClass);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

}
