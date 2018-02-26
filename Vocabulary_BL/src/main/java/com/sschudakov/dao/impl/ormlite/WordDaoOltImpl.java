package com.sschudakov.dao.impl.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordDaoOltImpl implements WordDao {
    private Dao<Word, Integer> wordsDao;
    private WordClassDao wordClassDao;

    public WordDaoOltImpl() {
        try {
            this.wordsDao = DaoManager.createDao(DatabaseManager.connectionSource, Word.class);
            this.wordClassDao = new WordClassDaoOltImpl();
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
    public Word findByValueAndLanguage(String value, Language language) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM words WHERE ")
                .append(Word.VALUE_COLUMN_NAME).append("=").append("\'" + value + "\'")
                .append(" AND ")
                .append(Word.LANGUAGE_COLUMN_NAME).append("=").append(language.getLanguageID());
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }

        return formWord(resultSet, value, language);
    }

    private Word formWord(ResultSet resultSet, String value, Language language) throws SQLException {
        Word result = new Word();
        result.setWordID(resultSet.getInt(Word.ID_COLUMN_NAME));
        result.setValue(value);
        result.setLanguage(language);
        result.setWordClass(this.wordClassDao.findById(resultSet.getInt(Word.WORD_CLASS_COLUMN_NAME)));
        return result;
    }

    @Override
    public List<Word> findByLanguage(Language language) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM words WHERE ")
                .append(Word.LANGUAGE_COLUMN_NAME).append("=").append(language.getLanguageID());

        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();
        return formWordsCollection(resultSet, language);
    }

    private List<Word> formWordsCollection(ResultSet resultSet, Language language) throws SQLException {
        List<Word> result = new ArrayList<>();
        Word word;
        while (resultSet.next()) {
            word = new Word();
            word.setWordID(resultSet.getInt(Word.ID_COLUMN_NAME));
            word.setValue(resultSet.getString(Word.VALUE_COLUMN_NAME));
            word.setWordClass(this.wordClassDao.findByName(resultSet.getString(Word.VALUE_COLUMN_NAME)));
            word.setLanguage(language);
            result.add(word);
        }
        return result;
    }


    @Override
    public Collection<Word> findAll() throws SQLException {
        return this.wordsDao.queryForAll();
    }

    @Override
    public void remove(Integer wordID) throws SQLException {
        this.wordsDao.deleteById(wordID);
    }

}
