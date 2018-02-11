package com.sschudakov.dao.impl.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public Collection<WordMeaningRelationship> findByWord(String word) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM word meaning relationship WHERE " +
                                WordMeaningRelationship.WORD_COLUMN_NAME + " = " + word
                );
        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
    }

    @Override
    public Collection<WordMeaningRelationship> findByMeaning(String meaning) throws SQLException {
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM word meaning relationship WHERE " +
                                WordMeaningRelationship.MEANING_COLUMN_NAME + " = " + "\'" + meaning + "\'"
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
            /*wcr.setWord();
            wcr.setWordCollection();*/
            result.add(wcr);
        }
        //TODO: implement
        /*return result;*/
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
