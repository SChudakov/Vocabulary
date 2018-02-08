package com.sschudakov.service.interf;

import com.sschudakov.entity.WordCollection;

import java.sql.SQLException;

public interface WordCollectionSrv {
    void create(String wordCollectionName) throws SQLException;

    public void delete(Integer wordCollectionId) throws SQLException ;

    WordCollection update(WordCollection wordCollection) throws SQLException;
}
