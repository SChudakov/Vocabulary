package com.sschudakov.dao.interf;

import com.sschudakov.entity.WordCollectionRelationship;

import java.sql.SQLException;
import java.util.List;

public interface WCRDao {

    void save(WordCollectionRelationship wordCollectionRelationship) throws SQLException;

    WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) throws SQLException;

    WordCollectionRelationship findById(Integer id) throws SQLException;

    WordCollectionRelationship findByName(String name) throws SQLException;

    List<WordCollectionRelationship> findAll() throws SQLException;

    void remove(Integer wordCollectionRelationshipID) throws SQLException;
}
