package com.sschudakov.service.interf;

import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface WMRSrv {

    void create(Word word, Word meaning) throws SQLException;

    WordMeaningRelationship findById(Integer id) throws SQLException;

    Collection<WordMeaningRelationship> findByWordAndLanguage(String word, String language) throws SQLException;

    Collection<WordMeaningRelationship> findByMeaningAndLanguage(String meaning, String language) throws SQLException;

    List<WordMeaningRelationship> findAll() throws SQLException;

    WordMeaningRelationship update(WordMeaningRelationship wmr) throws SQLException;

    void delete(Integer wmrId) throws SQLException;
    public Collection<Word> findWordMeanings(String word, String wordLanguage, String meaningsLanguage) throws SQLException;
}
