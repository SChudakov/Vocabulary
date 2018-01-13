package com.sschudakov.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;
import java.util.List;

public class WMRDaoImpl implements WMRDao {
    private Dao<WordMeaningRelationship, Integer> wordMeaningRelationshipsDao;

    public WMRDaoImpl() {
        try {
            this.wordMeaningRelationshipsDao = DaoManager.createDao(DatabaseManager.connectionSource, WordMeaningRelationship.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(WordMeaningRelationship wordMeaningRelationship) throws SQLException {
        this.wordMeaningRelationshipsDao.create(wordMeaningRelationship);
    }

    @Override
    public WordMeaningRelationship update(WordMeaningRelationship wordMeaningRelationship) throws SQLException {
        this.wordMeaningRelationshipsDao.update(wordMeaningRelationship);
        return wordMeaningRelationship;
    }

    @Override
    public WordMeaningRelationship findById(Integer id) throws SQLException {
        return this.wordMeaningRelationshipsDao.queryForId(id);
    }

    @Override
    public WordMeaningRelationship findByName(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<WordMeaningRelationship> findAll() throws SQLException {
        return this.wordMeaningRelationshipsDao.queryForAll();
    }

    @Override
    public void remove(Integer wordMeaningRelationshipID) throws SQLException {
        this.wordMeaningRelationshipsDao.deleteById(wordMeaningRelationshipID);
    }
}
