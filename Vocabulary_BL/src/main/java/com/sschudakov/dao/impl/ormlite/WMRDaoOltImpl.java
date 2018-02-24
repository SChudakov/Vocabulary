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

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId);

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
    }

    @Override
    public Collection<WordMeaningRelationship> findByMeaningId(int meaningId) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships WHERE ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(meaningId);

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
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

    @Override
    public void remove(Integer wordId, Integer meaningId) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE * FROM word_meaning_relationships WHERE ")
                .append("(").append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                .append("AND")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(meaningId).append(")")
                .append("OR")
                .append("(").append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(wordId)
                .append("AND")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(meaningId).append(")");

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }

    @Override
    public void removeByWordId(Integer wordId) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE * FROM word_meaning_relationships WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                .append("OR")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(wordId);

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }

    @Override
    public Collection<Integer> findMeaningsIds(int wordId, int meaningsLanguageId) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT word_meaning_relationships.")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME)
                .append(" FROM word_meaning_relationships INNER JOIN words ON ")
                .append("word_meaning_relationships.").append(WordMeaningRelationship.MEANING_COLUMN_NAME)
                .append("=")
                .append("words.").append(Word.ID_COLUMN_NAME)
                .append(" WHERE ")
                .append("word_meaning_relationships.").append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                .append(" AND ")
                .append(" words.").append(Word.LANGUAGE_COLUMN_NAME).append("=").append(meaningsLanguageId);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        return formIdsCollections(statement.getResultSet());
    }

    private static Collection<Integer> formIdsCollections(ResultSet resultSet) throws SQLException {
        Collection<Integer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getInt(WordMeaningRelationship.MEANING_COLUMN_NAME));
        }
        return result;
    }
}
