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
        PreparedStatement statement = DatabaseManager
                .connection.prepareStatement(
                        "SELECT * FROM words WHERE " +
                                Word.VALUE_COLUMN_NAME + " = " + "\'" + value + "\'" +
                                " AND " + Word.LANGUAGE_COLUMN_NAME + " = " + language.getLanguageID()
                );
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
    public List<Word> findAll() throws SQLException {
        return this.wordsDao.queryForAll();
    }

    @Override
    public void remove(Integer wordID) throws SQLException {
        this.wordsDao.deleteById(wordID);
    }

}
