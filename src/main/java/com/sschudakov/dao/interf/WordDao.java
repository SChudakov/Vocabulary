package com.sschudakov.dao.interf;

import com.sschudakov.entity.Word;

import java.sql.SQLException;
import java.util.List;

public interface WordDao {
    void create(Word word) throws SQLException;

    Word update(Word word) throws SQLException;

    Word findById(Integer id) throws SQLException;

    Word findByName(String name) throws SQLException;

    List<Word> findAll() throws SQLException;

    void remove(Integer wordID) throws SQLException;
}
