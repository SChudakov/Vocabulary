package com.sschudakov.dao.interf;

import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;
import java.util.List;

public interface WMRDao {
    void save(WordMeaningRelationship wordMeaningRelationship) throws SQLException;

    WordMeaningRelationship update(WordMeaningRelationship wordMeaningRelationship) throws SQLException;

    WordMeaningRelationship findById(Integer id) throws SQLException;

    WordMeaningRelationship findByName(String name) throws SQLException;

    List<WordMeaningRelationship> findAll() throws SQLException;

    void remove(Integer wordMeaningRelationshipID) throws SQLException;
}
