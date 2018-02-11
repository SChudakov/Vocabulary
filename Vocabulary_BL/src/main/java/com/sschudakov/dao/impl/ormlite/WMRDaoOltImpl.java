package com.sschudakov.dao.impl.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WMRDaoOltImpl implements WMRDao {
    private Dao<WordMeaningRelationship, Integer> wordMeaningRelationshipsDao;
    private WordDao wordDao;

    public WMRDaoOltImpl() {
        try {
            this.wordMeaningRelationshipsDao = DaoManager.createDao(DatabaseManager.connectionSource, WordMeaningRelationship.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.wordDao = new WordDaoOltImpl();
    }

    @Override
    public void save(WordMeaningRelationship wordMeaningRelationship) throws SQLException {
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

    public Collection<WordMeaningRelationship> findByWordId(int wordId) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM word_meaning_relationship WHERE " +
                                WordMeaningRelationship.WORD_COLUMN_NAME + " = " + wordId
                );
        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
    }

    @Override
    public Collection<WordMeaningRelationship> findByMeaningId(int meaningId) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM word_meaning_relationship WHERE " +
                                WordMeaningRelationship.MEANING_COLUMN_NAME + " = " + "\'" + meaningId + "\'"
                );
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
    }


    private Collection<WordMeaningRelationship> formWMRCollection(ResultSet resultSet) throws SQLException {
        Collection<WordMeaningRelationship> result = new ArrayList<>();
        WordMeaningRelationship wcr;
        while (resultSet.next()) {
            wcr = new WordMeaningRelationship();
            wcr.setWordMeaningRelationshipID(resultSet.getInt(WordMeaningRelationship.ID_COLUMN_NAME));
            wcr.setWord(this.wordDao.findById(resultSet.getInt(WordMeaningRelationship.WORD_COLUMN_NAME)));
            wcr.setMeaning(this.wordDao.findById(resultSet.getInt(WordMeaningRelationship.MEANING_COLUMN_NAME)));
            result.add(wcr);
        }
        return result;
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
