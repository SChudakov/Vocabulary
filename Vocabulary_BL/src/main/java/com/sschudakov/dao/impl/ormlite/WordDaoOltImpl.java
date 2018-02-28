package com.sschudakov.dao.impl.ormlite;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Word;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordDaoOltImpl implements WordDao {
    private WordClassDao wordClassDao;
    private LanguageDao languageDao;

    public WordDaoOltImpl() {
        this.wordClassDao = new WordClassDaoOltImpl();
        this.languageDao = new LanguageDaoOltImpl();
    }

    @Override
    public void save(Word word) throws SQLException {
        StringBuilder insertQuery = new StringBuilder("");
        insertQuery.append("INSERT INTO words")
                .append("(").append(Word.VALUE_COLUMN_NAME).append(",")
                .append(Word.WORD_CLASS_COLUMN_NAME).append(",")
                .append(Word.LANGUAGE_COLUMN_NAME).append(")")
                .append(" VALUES ")
                .append("(").append("\'" + word.getValue() + "\'").append(",")
                .append(word.getWordClass().getId()).append(",")
                .append(word.getLanguage().getId()).append(")").append(";");
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(insertQuery.toString());
        insertStatement.execute();
    }

    @Override
    public Word update(Word word) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("UPDATE words")
                .append(" SET ")
                .append(Word.VALUE_COLUMN_NAME).append("=").append("\'" + word.getValue() + "\'").append(",")
                .append(Word.WORD_CLASS_COLUMN_NAME).append("=").append(word.getWordClass().getId()).append(",")
                .append(Word.LANGUAGE_COLUMN_NAME).append("=").append(word.getLanguage().getId())
                .append(" WHERE ")
                .append(Word.ID_COLUMN_NAME).append("=").append(word.getWordID()).append(";");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        return word;
    }

    @Override
    public Word findById(Integer id) throws SQLException {
        StringBuilder selectQuery = new StringBuilder("");
        selectQuery.append("SELECT * FROM words ")
                .append(" WHERE ")
                .append(Word.ID_COLUMN_NAME).append("=").append(id);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(selectQuery.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formWord(resultSet);
    }

    @Override
    public Word findByValueAndLanguageId(String value, Integer languageId) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM words WHERE ")
                .append(Word.VALUE_COLUMN_NAME).append("=").append("\'" + value + "\'")
                .append(" AND ")
                .append(Word.LANGUAGE_COLUMN_NAME).append("=").append(languageId);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }

        return formWord(resultSet);
    }

    private Word formWord(ResultSet resultSet) throws SQLException {
        Word result = new Word();
        result.setWordID(resultSet.getInt(Word.ID_COLUMN_NAME));
        result.setValue(resultSet.getString(Word.VALUE_COLUMN_NAME));
        result.setLanguage(this.languageDao.findById(resultSet.getInt(Word.LANGUAGE_COLUMN_NAME)));
        result.setWordClass(this.wordClassDao.findById(resultSet.getInt(Word.WORD_CLASS_COLUMN_NAME)));
        return result;
    }

    @Override
    public List<Word> findByLanguageId(Integer languageId) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM words WHERE ")
                .append(Word.LANGUAGE_COLUMN_NAME).append("=").append(languageId);

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();
        return formWordsCollection(resultSet);
    }

    @Override
    public Collection<Word> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM words");
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWordsCollection(resultSet);
    }

    private List<Word> formWordsCollection(ResultSet resultSet) throws SQLException {
        List<Word> result = new ArrayList<>();
        Word word;
        while (resultSet.next()) {
            word = new Word();
            word.setWordID(resultSet.getInt(Word.ID_COLUMN_NAME));
            word.setValue(resultSet.getString(Word.VALUE_COLUMN_NAME));
            word.setWordClass(this.wordClassDao.findByName(resultSet.getString(Word.VALUE_COLUMN_NAME)));
            word.setLanguage(this.languageDao.findById(resultSet.getInt(Word.LANGUAGE_COLUMN_NAME)));
            result.add(word);
        }
        return result;
    }

    @Override
    public void remove(Integer wordID) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE FROM words")
                .append(" WHERE ")
                .append(Word.ID_COLUMN_NAME).append("=").append(wordID);

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }

}
