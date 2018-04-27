package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.database.DatabaseManager;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class WordDaoJdbcImpl implements WordDao {

    private static Logger logger = LogManager.getLogger(WordDaoJdbcImpl.class);

    //-------------- helping dao objects ---------------//

    private LanguageDao languageDao;
    private WordClassDao wordClassDao;


    //-------------- constructor ---------------//

    public WordDaoJdbcImpl(LanguageDao languageDao, WordClassDao wordClassDao) {
        this.languageDao = languageDao;
        this.wordClassDao = wordClassDao;
    }


    //-------------- save  ---------------//

    @Override
    public void save(Word word) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("INSERT INTO words")
                .append("(").append(Word.VALUE_CN).append(",")
                .append(Word.WORD_CLASS_CN).append(",")
                .append(Word.LANGUAGE_CN).append(")")
                .append(" VALUES ")
                .append("(").append("\'").append(word.getValue()).append("\'").append(",")
                .append(word.getWordClass().getId()).append(",")
                .append(word.getLanguage().getId()).append(")").append(";");
        logger.info(query);
        PreparedStatement insertStatement = DatabaseManager.connection.prepareStatement(query.toString());
        insertStatement.execute();
    }


    //-------------- update ---------------//

    @Override
    public Word update(Word word) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("UPDATE words")
                .append(" SET ")
                .append(Word.VALUE_CN).append("=").append("\'").append(word.getValue()).append("\'").append(",")
                .append(Word.WORD_CLASS_CN).append("=").append(word.getWordClass().getId()).append(",")
                .append(Word.LANGUAGE_CN).append("=").append(word.getLanguage().getId())
                .append(" WHERE ")
                .append(Word.ID_CN).append("=").append(word.getId()).append(";");
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
        return word;
    }


    //-------------- find ---------------//

    @Override
    public Word findById(Integer id) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM words ")
                .append(" WHERE ")
                .append(Word.ID_CN).append("=").append(id);
        logger.info(query);
        PreparedStatement selectStatement = DatabaseManager.connection.prepareStatement(query.toString());
        selectStatement.execute();
        ResultSet resultSet = selectStatement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }
        return formWord(resultSet);
    }

    @Override
    public Word findByValueAndLanguage(String value, Language language) throws SQLException {

        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM words WHERE ")
                .append(Word.VALUE_CN).append("=").append("\'").append(value).append("\'")
                .append(" AND ")
                .append(Word.LANGUAGE_CN).append("=").append(language.getId());
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        if (!resultSet.next()) {
            return null;
        }

        Word result = formWord(resultSet);

        if (resultSet.next()) {
            throw new IllegalArgumentException("multiple words match the value "
                    + value + " and language " + language);
        }

        return result;
    }

    private Word formWord(ResultSet resultSet) throws SQLException {
        Word result = new Word();
        result.setId(resultSet.getInt(Word.ID_CN));
        result.setValue(resultSet.getString(Word.VALUE_CN));
        result.setLanguage(this.languageDao.findById(resultSet.getInt(Word.LANGUAGE_CN)));
        result.setWordClass(this.wordClassDao.findById(resultSet.getInt(Word.WORD_CLASS_CN)));
        return result;
    }

    @Override
    public List<Word> findByLanguage(Language language) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM words WHERE ")
                .append(Word.LANGUAGE_CN).append("=").append(language.getId());
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();
        return formWordsList(resultSet);
    }

    @Override
    public Collection<Word> findAll() throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("SELECT * FROM words");
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        return formWordsList(resultSet);
    }

    private List<Word> formWordsList(ResultSet resultSet) throws SQLException {
        List<Word> result = new ArrayList<>();
        Word word;
        while (resultSet.next()) {
            word = new Word();
            word.setId(resultSet.getInt(Word.ID_CN));
            word.setValue(resultSet.getString(Word.VALUE_CN));
            word.setWordClass(this.wordClassDao.findById(resultSet.getInt(Word.WORD_CLASS_CN)));
            word.setLanguage(this.languageDao.findById(resultSet.getInt(Word.LANGUAGE_CN)));
            result.add(word);
        }
        return result;
    }


    //-------------- remove ---------------//

    @Override
    public void remove(Integer wordID) throws SQLException {
        StringBuilder query = new StringBuilder("");
        query.append("DELETE FROM words")
                .append(" WHERE ")
                .append(Word.ID_CN).append("=").append(wordID);
        logger.info(query);
        PreparedStatement statement = DatabaseManager.connection.prepareStatement(query.toString());
        statement.execute();
    }

}
