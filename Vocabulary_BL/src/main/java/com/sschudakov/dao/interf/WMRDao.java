package com.sschudakov.dao.interf;

import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface WMRDao {
    void save(WordMeaningRelationship wordMeaningRelationship) throws SQLException;

    WordMeaningRelationship update(WordMeaningRelationship wordMeaningRelationship) throws SQLException;

    WordMeaningRelationship findById(Integer id) throws SQLException;

    Collection<WordMeaningRelationship> findByWordId(int wordId) throws SQLException;

    Collection<WordMeaningRelationship> findByMeaningId(int meaningId) throws SQLException;

    List<WordMeaningRelationship> findAll() throws SQLException;

    Collection<Integer> findMeaningsIds(int wordId, int meaningsLanguageId) throws SQLException;

    void remove(Integer wordMeaningRelationshipID) throws SQLException;

    void remove(Integer wordId, Integer meaningId) throws SQLException;

    void removeByWordId(Integer wordId) throws SQLException;
}
