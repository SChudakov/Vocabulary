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

    @Override
    public void save(WordClass wordClass) throws SQLException {
        this.wordClassDao.create(wordClass);
    }

    @Override
    public WordClass update(WordClass wordClass) throws SQLException {
        this.wordClassDao.update(wordClass);
        return wordClass;
    }

    @Override
    public WordClass findById(Integer id) throws SQLException {
        return this.wordClassDao.queryForId(id);
    }

    @Override
    public WordClass findByName(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<WordClass> findAll() throws SQLException {
        return this.wordClassDao.queryForAll();
    }

    @Override
    public void remove(Integer wordClassID) throws SQLException {
        this.wordClassDao.deleteById(wordClassID);
    }
}
