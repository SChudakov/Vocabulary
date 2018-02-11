package com.sschudakov.service.interf;

import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;
import java.util.Collection;

public interface WMRSrv {

    void create(Word word, Word meaning) throws SQLException;

    Collection<WordMeaningRelationship> findByWordAndLanguage(String word, String language) throws SQLException;

    Collection<WordMeaningRelationship> findByMeaningAndLanguage(String meaning, String language) throws SQLException;

    WordMeaningRelationship update(WordMeaningRelationship wmr) throws SQLException;

    void delete(Integer wmrId) throws SQLException;

}
