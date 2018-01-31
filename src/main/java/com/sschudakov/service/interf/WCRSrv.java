package com.sschudakov.service.interf;

import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;

public interface WCRSrv {

    public void create(Word word, WordCollection wordCollection) throws SQLException;

    public void delete(Integer wcrId) throws SQLException;

    WordCollectionRelationship update(WordCollectionRelationship wcr) throws SQLException;
}
