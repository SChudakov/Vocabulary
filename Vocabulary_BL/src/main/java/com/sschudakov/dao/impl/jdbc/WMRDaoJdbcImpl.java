package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WMRDaoJdbcImpl implements WMRDao {

    //-------------- helping dao object ---------------//

    private WordDao wordDao;


    //-------------- constructor ---------------//

    public WMRDaoJdbcImpl(WordDao wordDao) {
        this.wordDao = wordDao;
    }


    //-------------- save  ---------------//

    @Override
    public void save(WordMeaningRelationship wordMeaningRelationship) throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO word_meaning_relationships")
                .append("(").append(WordMeaningRelationship.WORD_COLUMN_NAME).append(",")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append(wordMeaningRelationship.getWord().getId()).append(",")
                .append(wordMeaningRelationship.getMeaning().getId()).append(")").append(";");
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }


    //-------------- update ---------------//

    @Override
    public WordMeaningRelationship update(WordMeaningRelationship wordMeaningRelationship) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("UPDATE word_meaning_relationships")
                .append(" SET ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(wordMeaningRelationship.getWord().getId()).append(",")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(wordMeaningRelationship.getMeaning().getId())
                .append(" WHERE ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordMeaningRelationship.getId()).append(";");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        return wordMeaningRelationship;
    }


    //-------------- find ---------------//

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
    public Collection<Integer> findWordMeaningsIds(Word word, Language meaningsLanguage) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT word_meaning_relationships.")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME)
                .append(" FROM word_meaning_relationships INNER JOIN words")
                .append(" ON ")
                .append("word_meaning_relationships.").append(WordMeaningRelationship.MEANING_COLUMN_NAME)
                .append("=")
                .append("words.").append(Word.ID_COLUMN_NAME)
                .append(" WHERE ")
                .append("word_meaning_relationships.").append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(word.getId())
                .append(" AND ")
                .append(" words.").append(Word.LANGUAGE_COLUMN_NAME).append("=").append(meaningsLanguage.getId());
        System.out.println(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        return formIdsCollections(statement.getResultSet());
    }

    @Override
    public WordMeaningRelationship findByWordAndMeaning(Word word, Word meaning) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(word.getId())
                .append(" AND ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(meaning.getId());

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        if (!resultSet.next()) {
            return null;
        }
        return formWMR(resultSet);
    }

    @Override
    public Collection<WordMeaningRelationship> findByWord(Word word) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(word.getId());

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
    }

    @Override
    public Collection<WordMeaningRelationship> findByMeaning(Word meaning) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(meaning.getId());

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
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


    //-------------- remove ---------------//

    @Override
    public void remove(Integer wordMeaningRelationshipID) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(wordMeaningRelationshipID);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }


    //-------------- helping methods ---------------//

    private static Collection<Integer> formIdsCollections(ResultSet resultSet) throws SQLException {
        Collection<Integer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getInt(WordMeaningRelationship.MEANING_COLUMN_NAME));
        }
        return result;
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
}
