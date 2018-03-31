package com.sschudakov.dao.interf;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface WMRDao {


    //-------------- save  ---------------//

    void save(WordMeaningRelationship wordMeaningRelationship) throws SQLException;


    //-------------- update ---------------//

    WordMeaningRelationship update(WordMeaningRelationship wordMeaningRelationship) throws SQLException;


    //-------------- find ---------------//

    WordMeaningRelationship findById(Integer id) throws SQLException;

    List<WordMeaningRelationship> findByWord(Word word) throws SQLException;

    List<WordMeaningRelationship> findByMeaning(Word meaning) throws SQLException;

    List<Word> findWordMeanings(Word word, Language meaningsLanguage) throws SQLException;

    Map<Word, List<Word>> findWordsMeanings(List<Word> words, Language meaningsLanguage) throws SQLException;

    WordMeaningRelationship findByWordAndMeaning(Word word, Word meaning) throws SQLException;

    List<WordMeaningRelationship> findAll() throws SQLException;


    //-------------- remove ---------------//

    void remove(Integer wordMeaningRelationshipID) throws SQLException;
}
