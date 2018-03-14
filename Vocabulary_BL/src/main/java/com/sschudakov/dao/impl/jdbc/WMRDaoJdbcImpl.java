package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;
import com.sschudakov.logging.LoggersManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        StringBuilder query = new StringBuilder("");
        query.append("INSERT INTO word_meaning_relationships")
                .append("(").append(WordMeaningRelationship.WORD_COLUMN_NAME).append(",")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append(wordMeaningRelationship.getWord().getId()).append(",")
                .append(wordMeaningRelationship.getMeaning().getId()).append(")").append(";");
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(query.toString());
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
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        return wordMeaningRelationship;
    }


    //-------------- find ---------------//

    @Override
    public WordMeaningRelationship findById(Integer id) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.ID_COLUMN_NAME).append("=").append(id);
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formWMR(resultSet);
    }

    @Override
    public List<Integer> findWordMeaningsIds(Word word, Language meaningsLanguage) throws SQLException {

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
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        return formIdsCollections(statement.getResultSet());
    }

    private static List<Integer> formIdsCollections(ResultSet resultSet) throws SQLException {
        List<Integer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getInt(WordMeaningRelationship.MEANING_COLUMN_NAME));
        }
        return result;
    }


    @Override
    public Map<Word, List<Word>> findWordsMeanings(List<Word> words, Language meaningsLanguage) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT word_meaning_relationships.").append(WordMeaningRelationship.WORD_COLUMN_NAME).append(",")
                .append("word_meaning_relationships.").append(WordMeaningRelationship.MEANING_COLUMN_NAME)
                .append(" FROM ")
                .append("word_meaning_relationships INNER JOIN words")
                .append(" ON ")
                .append("word_meaning_relationships.").append(WordMeaningRelationship.MEANING_COLUMN_NAME)
                .append("=")
                .append("words.").append(Word.ID_COLUMN_NAME)
                .append(" WHERE ")
                .append("word_meaning_relationships.").append(WordMeaningRelationship.WORD_COLUMN_NAME)
                .append(" IN ")
                .append(formQueryInSection(words))
                .append(" AND ")
                .append("words.").append(Word.LANGUAGE_COLUMN_NAME).append("=").append(meaningsLanguage.getId());
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        return formDataMap(statement.getResultSet());
    }

    private Map<Word, List<Word>> formDataMap(ResultSet resultSet) throws SQLException {
        Map<Word, List<Word>> result = new HashMap<>();
        Map<Integer, List<Integer>> idsMap = formIdsMap(resultSet);

        for (Map.Entry<Integer, List<Integer>> idsMapEntry : idsMap.entrySet()) {
            Word entryWord = this.wordDao.findById(idsMapEntry.getKey());
            List<Word> entryMeanings = new ArrayList<>();
            for (Integer meaningId : idsMapEntry.getValue()) {
                entryMeanings.add(this.wordDao.findById(meaningId));
            }
            result.put(entryWord, entryMeanings);
        }
        return result;
    }

    private static Map<Integer, List<Integer>> formIdsMap(ResultSet resultSet) throws SQLException {
        Map<Integer, List<Integer>> result = new HashMap<>();
        while (resultSet.next()) {
            Integer wordId = resultSet.getInt(WordMeaningRelationship.WORD_COLUMN_NAME);
            if (result.get(wordId) == null) {
                result.put(wordId, new ArrayList<>());
            }
            result.get(wordId).add(resultSet.getInt(WordMeaningRelationship.MEANING_COLUMN_NAME));
        }
        return result;
    }

    private static String formQueryInSection(List<Word> words) {
        StringBuilder result = new StringBuilder("");
        result.append("(");

        words.forEach(word -> result.append(word.getId()).append(","));

        result.deleteCharAt(result.length() - 1).append(")");
        return result.toString();
    }


    @Override
    public WordMeaningRelationship findByWordAndMeaning(Word word, Word meaning) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(word.getId())
                .append(" AND ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(meaning.getId());
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        if (!resultSet.next()) {
            return null;
        }
        return formWMR(resultSet);
    }

    @Override
    public List<WordMeaningRelationship> findByWord(Word word) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.WORD_COLUMN_NAME).append("=").append(word.getId());
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
    }

    @Override
    public List<WordMeaningRelationship> findByMeaning(Word meaning) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships")
                .append(" WHERE ")
                .append(WordMeaningRelationship.MEANING_COLUMN_NAME).append("=").append(meaning.getId());
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWMRCollection(resultSet);
    }

    @Override
    public List<WordMeaningRelationship> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM word_meaning_relationships");
        LoggersManager.getParsingLogger().info(query);
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
        LoggersManager.getParsingLogger().info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }


    //-------------- helping methods ---------------//

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
