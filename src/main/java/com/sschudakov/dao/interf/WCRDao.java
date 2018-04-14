package com.sschudakov.dao.interf;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository

public interface WCRDao {


    //-------------- save  ---------------//

    void save(WordCollectionRelationship wordCollectionRelationship) throws SQLException;


    //-------------- update ---------------//

    WordCollectionRelationship update(WordCollectionRelationship wordCollectionRelationship) throws SQLException;


    //-------------- find ---------------//

    WordCollectionRelationship findById(Integer id) throws SQLException;

    List<WordCollectionRelationship> findRelationshipsByWord(Word word) throws SQLException;

    List<WordCollectionRelationship> findByCollection(WordCollection collection) throws SQLException;

    List<WordCollectionRelationship> findByCollectionAndLanguage(WordCollection collection, Language language) throws SQLException;

    WordCollectionRelationship findByWordAndCollection(Word word, WordCollection collection) throws SQLException;

    List<WordCollectionRelationship> findAll() throws SQLException;


    //-------------- remove ---------------//

    void remove(Integer wordCollectionRelationshipID) throws SQLException;
}
