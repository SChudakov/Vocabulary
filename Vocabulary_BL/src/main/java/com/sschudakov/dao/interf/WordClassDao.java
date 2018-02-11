package com.sschudakov.dao.interf;

import com.sschudakov.entity.WordClass;

import java.sql.SQLException;
import java.util.List;

public interface WordClassDao {
    void save(WordClass wordClass) throws SQLException;

    WordClass findById(Integer id) throws SQLException;

    WordClass findByName(String name) throws SQLException;

    List<WordClass> findAll() throws SQLException;
}
