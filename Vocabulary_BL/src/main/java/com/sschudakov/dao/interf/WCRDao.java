package com.sschudakov.dao.interf;

import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface WCRDao {

    void save(WordCollectionRelationship wordCollectionRelationship) throws SQLException;


    WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) throws SQLException;


    WordCollectionRelationship findById(Integer id) throws SQLException;

    Collection<WordCollectionRelationship> findByWordId(int wordId) throws SQLException;

    Collection<WordCollectionRelationship> findByCollectionId(int collectionId) throws SQLException;

    List<WordCollectionRelationship> findAll() throws SQLException;


    void remove(Integer wordCollectionRelationshipID) throws SQLException;

    void removeByWordAndCollectionId(Integer wordId, Integer collectionId) throws SQLException;

    void removeByWordId(Integer wordId) throws SQLException;

    void removeByCollectionId(Integer collectionId) throws SQLException;
}
