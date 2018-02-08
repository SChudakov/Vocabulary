package com.sschudakov.service.interf;

import com.sschudakov.entity.WordClass;

import java.sql.SQLException;

public interface WordClassSrv {
    void create(String wordClassName) throws SQLException;

    void delete(Integer wordClassId) throws SQLException;

    WordClass update(WordClass wordClass) throws SQLException;
}
