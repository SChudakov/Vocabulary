package com.sschudakov.service.interf;

import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;
import java.util.Collection;

public interface WCRSrv {

    void create(Word word, WordCollection wordCollection) throws SQLException;

    WordCollectionRelationship update(WordCollectionRelationship wcr) throws SQLException;

    Collection<WordCollectionRelationship> findByWordAndLanguage(String word, String language) throws SQLException;

    Collection<WordCollectionRelationship> findByCollection(String collection) throws SQLException;

    void delete(Integer wcrId) throws SQLException;
}
