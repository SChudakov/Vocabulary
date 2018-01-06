package com.sschudakov.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordCollection;

import java.sql.SQLException;
import java.util.List;

public class WordCollectionDaoImpl implements WordCollectionDao{
    private Dao<WordCollection, Integer> wordCollectionsDao;

    public WordCollectionDaoImpl() {
        try {
            this.wordCollectionsDao = DaoManager.createDao(DatabaseManager.connectionSource, WordCollection.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(WordCollection wordCollection) throws SQLException {
        this.wordCollectionsDao.create(wordCollection);
    }

    public WordCollection update(WordCollection wordCollection) throws SQLException {
        this.wordCollectionsDao.update(wordCollection);
        return wordCollection;
    }

    public WordCollection findById(Integer id) throws SQLException {
        return this.wordCollectionsDao.queryForId(id);
    }

    public WordCollection findByName(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public List<WordCollection> findAll() throws SQLException {
        return this.wordCollectionsDao.queryForAll();
    }

    public void remove(Integer wordCollectionID) throws SQLException {
        this.wordCollectionsDao.deleteById(wordCollectionID);
    }
}
