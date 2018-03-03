package com.sschudakov.dao.interf;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface WMRDao {
    void save(WordMeaningRelationship wordMeaningRelationship) throws SQLException;

    WordMeaningRelationship update(WordMeaningRelationship wordMeaningRelationship) throws SQLException;

    WordMeaningRelationship findById(Integer id) throws SQLException;

    Collection<WordMeaningRelationship> findByWord(Word word) throws SQLException;

    Collection<WordMeaningRelationship> findByMeaning(Word meaning) throws SQLException;

    Collection<Integer> findWordMeaningsIds(Word word, Language meaningsLanguage) throws SQLException;

    WordMeaningRelationship findByWordAndMeaning(Word word, Word meaning) throws SQLException;

    List<WordMeaningRelationship> findAll() throws SQLException;

    void remove(Integer wordMeaningRelationshipID) throws SQLException;
}
