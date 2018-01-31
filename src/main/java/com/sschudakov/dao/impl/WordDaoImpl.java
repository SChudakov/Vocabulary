package com.sschudakov.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Word;

import java.sql.SQLException;
import java.util.List;

public class WordDaoImpl implements WordDao {
    private Dao<Word, Integer> wordsDao;

    public WordDaoImpl() {
        try {
            this.wordsDao = DaoManager.createDao(DatabaseManager.connectionSource, Word.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Word word) throws SQLException {
        this.wordsDao.create(word);
    }

    @Override
    public Word update(Word word) throws SQLException {
        this.wordsDao.update(word);
        return word;
    }

    @Override
    public Word findById(Integer id) throws SQLException {
        return this.wordsDao.queryForId(id);
    }

    @Override
    public Word findByName(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Word> findAll() throws SQLException {
        return this.wordsDao.queryForAll();
    }

    @Override
    public void remove(Integer wordID) throws SQLException {
        this.wordsDao.deleteById(wordID);
    }

}
