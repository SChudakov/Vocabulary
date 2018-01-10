package com.sschudakov.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DatabaseAccessor {
    public static <T> Collection<T> uploadData(Class<T> tClass) {

        Dao<T, String> productDao = null;

        try {
            // Connect to database
            // create DAO object
            productDao = DaoManager.createDao(DatabaseManager.connectionSource, tClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // create Query Builder
        QueryBuilder<T, String> productsQueryBuilder = productDao.queryBuilder();

        // executing query
        List<T> result = null;
        try {
            result = productsQueryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
