package com.sschudakov.dao.impl.ormlite;

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
    private WordDao wordDao;

    public WMRDaoOltImpl() {
        this.wordDao = new WordDaoOltImpl();
    }

    @Override
    public void save(WordMeaningRelationship wordMeaningRelationship) throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_meaning_relationships")
                .append("(").append(WordMeaningRelationship.WORD_COLUMN_NAME).append(",")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append(wordMeaningRelationship.getWord().getWordID()).append(",")
                .append(wordMeaningRelationship.getMeaning().getWordID()).append(")").append(";");
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    @Override
    public WordMeaningRelationship update(WordMeaningRelationship wordMeaningRelationship) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("UPDATE word_meaning_relationships")
                .append(" SET ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordMeaningRelationship.getWord().getWordID()).append(",")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(wordMeaningRelationship.getMeaning().getWordID())
                .append(" WHERE ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordMeaningRelationship.getId()).append(";");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        return wordMeaningRelationship;
    }

    @Override
    public WordMeaningRelationship findById(Integer id) throws SQLException {
        StringBuilder selectQuery = new StringBuilder("");
        selectQuery.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(id);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(selectQuery.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formWMR(resultSet);
    }

    @Override
    public Collection<WordMeaningRelationship> findByWordId(int wordId) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId);

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
    }

    private List<WordMeaningRelationship> formWMRCollection(ResultSet resultSet) throws SQLException {
        List<WordMeaningRelationship> result = new ArrayList<>();
        WordMeaningRelationship wmr;
        while (resultSet.next()) {
            wmr = new WordMeaningRelationship();
            wmr.setId(resultSet.getInt(WordMeaningRelationship.ID_COLUMN_NAME));
            wmr.setWord(this.wordDao.findById(resultSet.getInt(WordMeaningRelationship.WORD_COLUMN_NAME)));
            wmr.setMeaning(this.wordDao.findById(resultSet.getInt(WordMeaningRelationship.MEANING_COLUMN_NAME)));
            result.add(wmr);
        }
        return result;
    }

    private WordMeaningRelationship formWMR(ResultSet resultSet) throws SQLException {
        WordMeaningRelationship wmr = new WordMeaningRelationship();
        wmr.setId(resultSet.getInt(WordMeaningRelationship.ID_COLUMN_NAME));
        wmr.setWord(this.wordDao.findById(resultSet.getInt(WordMeaningRelationship.WORD_COLUMN_NAME)));
        wmr.setMeaning(this.wordDao.findById(resultSet.getInt(WordMeaningRelationship.MEANING_COLUMN_NAME)));
        return wmr;
    }


    @Override
    public List<WordMeaningRelationship> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
    }

    @Override
    public void remove(Integer wordMeaningRelationshipID) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordMeaningRelationshipID);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }

    @Override
    public void remove(Integer wordId, Integer meaningId) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE FROM word_meaning_relationships")
                .append(" WHERE ")
                .append("(").append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                .append(" AND ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(meaningId).append(")")
                .append(" OR ")
                .append("(").append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(wordId)
                .append(" AND ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(meaningId).append(")");

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }

    @Override
    public void removeAllWordRelationships(Integer wordId) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                .append(" OR ")
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

    @Override
    public Collection<WordMeaningRelationship> findByWordAndMeaningIds(Integer wordId, Integer meaningId) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append("(").append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordId)
                .append(" AND ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(meaningId).append(")")
                .append(" OR ")
                .append("(").append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(meaningId)
                .append(" AND ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(wordId).append(")");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        return formWMRCollection(resultSet);
    }

    private static Collection<Integer> formIdsCollections(ResultSet resultSet) throws SQLException {
        Collection<Integer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getInt(WordMeaningRelationship.MEANING_COLUMN_NAME));
        }
        return result;
    }
}
