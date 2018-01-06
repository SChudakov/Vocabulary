package com.sschudakov.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordClass;

import java.sql.SQLException;
import java.util.List;

public class WordClassDaoImpl implements WordClassDao {
    private Dao<WordClass, Integer> wordClassDao;

    public WordClassDaoImpl() {
        try {
            this.wordClassDao = DaoManager.createDao(DatabaseManager.connectionSource, WordClass.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(WordClass wordClass) throws SQLException {
        this.wordClassDao.create(wordClass);
    }

    public WordClass update(WordClass wordClass) throws SQLException {
        this.wordClassDao.update(wordClass);
        return wordClass;
    }

    public WordClass findById(Integer id) throws SQLException {
        return this.wordClassDao.queryForId(id);
    }

    public WordClass findByName(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public List<WordClass> findAll() throws SQLException {
        return this.wordClassDao.queryForAll();
    }

    public void remove(Integer wordClassID) throws SQLException {
        this.wordClassDao.deleteById(wordClassID);
    }
}
