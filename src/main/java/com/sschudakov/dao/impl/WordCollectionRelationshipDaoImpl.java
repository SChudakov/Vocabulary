package com.sschudakov.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WordCollectionRelationshipDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;
import java.util.List;

public class WordCollectionRelationshipDaoImpl implements WordCollectionRelationshipDao {
    private Dao<WordCollectionRelationship, Integer> wordCollectionRelationshipsDao;

    public WordCollectionRelationshipDaoImpl() {
        try {
            this.wordCollectionRelationshipsDao = DaoManager.createDao(DatabaseManager.connectionSource, WordCollectionRelationship.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        this.wordCollectionRelationshipsDao.create(wordCollectionRelationship);
    }

    public WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        this.wordCollectionRelationshipsDao.update(wordCollectionRelationship);
        return wordCollectionRelationship;
    }

    public WordCollectionRelationship findById(Integer id) throws SQLException {
        return this.wordCollectionRelationshipsDao.queryForId(id);
    }

    public WordCollectionRelationship findByName(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public List<WordCollectionRelationship> findAll() throws SQLException {
        return this.wordCollectionRelationshipsDao.queryForAll();
    }

    public void remove(Integer wordCollectionRelationshipID) throws SQLException {
        this.wordCollectionRelationshipsDao.deleteById(wordCollectionRelationshipID);
    }
}
