package com.sschudakov.database;

import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class TableManager {
    public static <T> void createTable(Class<T> tClass) throws ClassNotFoundException, SQLException {
        /*TableUtils.createTableIfNotExists(DatabaseManager.connectionSource, tClass);*/
    }

    public static <T> void dropTable(Class<T> tClass) throws SQLException {
        /*TableUtils.dropTable(DatabaseManager.connectionSource, tClass, false);*/
    }
}
