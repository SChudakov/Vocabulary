package com.sschudakov.service.interf;

import com.sschudakov.entity.Word;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface WordSrv {

    void create(String word, String wordClass, String language) throws SQLException;

    Word update(Word word) throws SQLException;

    Word findById(Integer id) throws SQLException;

    Word findByValueAndLanguage(String value, String languageName) throws SQLException;

    Collection<Word> findAll() throws SQLException;

    List<Word> findByLanguage(String languageName) throws SQLException;

    void delete(Integer wordId) throws SQLException;

}
