package com.sschudakov.dao.interf;

import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface WCRDao {

    void save(WordCollectionRelationship wordCollectionRelationship) throws SQLException;


    WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) throws SQLException;


    WordCollectionRelationship findById(Integer id) throws SQLException;

    Collection<WordCollectionRelationship> findRelationshipsByWord(Word word) throws SQLException;

    Collection<WordCollectionRelationship> findWordsByCollection(WordCollection collection) throws SQLException;

    WordCollectionRelationship findByWordAndCollection(Word word, WordCollection collection) throws SQLException;

    List<WordCollectionRelationship> findAll() throws SQLException;


    void remove(Integer wordCollectionRelationshipID) throws SQLException;
}
