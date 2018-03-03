package com.sschudakov.dao.interf;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface WordDao {
    void save(Word word) throws SQLException;

    Word update(Word word) throws SQLException;

    Word findById(Integer id) throws SQLException;

    Word findByValueAndLanguage(String value, Language language) throws SQLException;

    List<Word> findByLanguage(Language language) throws SQLException;

    Collection<Word> findAll() throws SQLException;

    void remove(Integer wordID) throws SQLException;
}
