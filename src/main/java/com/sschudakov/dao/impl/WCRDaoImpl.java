package com.sschudakov.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;
import java.util.List;

public class WCRDaoImpl implements WCRDao {
    private Dao<WordCollectionRelationship, Integer> wordCollectionRelationshipsDao;

    public WCRDaoImpl() {
        try {
            this.wordCollectionRelationshipsDao = DaoManager.createDao(DatabaseManager.connectionSource, WordCollectionRelationship.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        this.wordCollectionRelationshipsDao.create(wordCollectionRelationship);
    }

    @Override
    public WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) throws SQLException {
        this.wordCollectionRelationshipsDao.update(wordCollectionRelationship);
        return wordCollectionRelationship;
    }

    @Override
    public WordCollectionRelationship findById(Integer id) throws SQLException {
        return this.wordCollectionRelationshipsDao.queryForId(id);
    }

    @Override
    public WordCollectionRelationship findByName(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<WordCollectionRelationship> findAll() throws SQLException {
        return this.wordCollectionRelationshipsDao.queryForAll();
    }

    @Override
    public void remove(Integer wordCollectionRelationshipID) throws SQLException {
        this.wordCollectionRelationshipsDao.deleteById(wordCollectionRelationshipID);
    }
}
