package com.sschudakov.service.interf;

import com.sschudakov.entity.WordClass;

import java.sql.SQLException;
import java.util.List;

public interface WordClassSrv {
    void create(String wordClassName) throws SQLException;

    WordClass findById(Integer id) throws SQLException;

    WordClass findByName(String name) throws SQLException;

    List<WordClass> findAll() throws SQLException;
}
