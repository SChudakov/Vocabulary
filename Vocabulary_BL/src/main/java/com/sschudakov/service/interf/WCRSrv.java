package com.sschudakov.service.interf;

import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface WCRSrv {

    void create(Word word, WordCollection wordCollection) throws SQLException;

    WordCollectionRelationship findById(Integer id) throws SQLException;

    Collection<WordCollectionRelationship> findByWordAndLanguage(String word, String language) throws SQLException;

    Collection<WordCollectionRelationship> findByCollection(String collection) throws SQLException;

    List<WordCollectionRelationship> findAll() throws SQLException;

    WordCollectionRelationship update(WordCollectionRelationship wcr) throws SQLException;

    void delete(Integer wcrId) throws SQLException;

    void delete(String word, String language, String collectionName) throws SQLException;
}
