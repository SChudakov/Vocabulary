package com.sschudakov.service.interf;

import com.sschudakov.entity.Word;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface WordSrv {

    void create(String word, String wordClass, String language,
                Collection<String> collectionsNames, Collection<String> meanings,
                String meaningsLanguage) throws SQLException;

    Word update(Word word) throws SQLException;

    Word findById(Integer id) throws SQLException;

    List<Word> findAll() throws SQLException;

    void delete(Integer wordId) throws SQLException;

}