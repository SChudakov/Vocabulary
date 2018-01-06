package com.sschudakov.dao.interf;

import com.sschudakov.entity.WordCollection;

import java.sql.SQLException;
import java.util.List;

public interface WordCollectionDao {

    void create(WordCollection wordCollection) throws SQLException;

    WordCollection update(WordCollection wordCollection) throws SQLException;

    WordCollection findById(Integer id) throws SQLException;

    WordCollection findByName(String name) throws SQLException;

    List<WordCollection> findAll() throws SQLException;

    void remove(Integer wordCollectionID) throws SQLException;
}
