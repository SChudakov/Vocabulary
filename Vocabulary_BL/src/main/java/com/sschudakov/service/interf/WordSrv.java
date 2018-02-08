package com.sschudakov.service.interf;

import com.sschudakov.entity.Word;

import java.sql.SQLException;
import java.util.Collection;

public interface WordSrv {

    void create(String word, String wordClass,
                Collection<String> collectionsNames, Collection<String> meanings) throws SQLException;

    void delete(Integer wordId) throws SQLException;

    Word update(Word word) throws SQLException;
}
