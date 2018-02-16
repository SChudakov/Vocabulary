package com.sschudakov.service.interf;

import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;
import java.util.Collection;

public interface WordCollectionSrv {
    void create(String wordCollectionName) throws SQLException;

    WordCollection update(WordCollection wordCollection) throws SQLException;

    void delete(Integer wordCollectionId) throws SQLException;

}
