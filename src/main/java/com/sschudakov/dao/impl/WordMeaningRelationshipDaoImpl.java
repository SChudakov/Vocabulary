package com.sschudakov.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WordMeaningRelationshipDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;
import java.util.List;

public class WordMeaningRelationshipDaoImpl implements WordMeaningRelationshipDao{
    private Dao<WordMeaningRelationship, Integer> wordMeaningRelationshipsDao;

    public WordMeaningRelationshipDaoImpl() {
        try {
            this.wordMeaningRelationshipsDao = DaoManager.createDao(DatabaseManager.connectionSource, WordMeaningRelationship.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(WordMeaningRelationship wordMeaningRelationship) throws SQLException {
        this.wordMeaningRelationshipsDao.create(wordMeaningRelationship);
    }

    public WordMeaningRelationship update(WordMeaningRelationship wordMeaningRelationship) throws SQLException {
        this.wordMeaningRelationshipsDao.update(wordMeaningRelationship);
        return wordMeaningRelationship;
    }

    public WordMeaningRelationship findById(Integer id) throws SQLException {
        return this.wordMeaningRelationshipsDao.queryForId(id);
    }

    public WordMeaningRelationship findByName(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public List<WordMeaningRelationship> findAll() throws SQLException {
        return this.wordMeaningRelationshipsDao.queryForAll();
    }

    public void remove(Integer wordMeaningRelationshipID) throws SQLException {
        this.wordMeaningRelationshipsDao.deleteById(wordMeaningRelationshipID);
    }
}
