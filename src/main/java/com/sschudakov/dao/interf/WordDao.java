package com.sschudakov.dao.interf;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
@Repository
public interface WordDao {


    //-------------- save  ---------------//

    void save(Word word) throws SQLException;


    //-------------- update ---------------//

    Word update(Word word) throws SQLException;


    //-------------- find ---------------//

    Word findById(Integer id) throws SQLException;

    Word findByValueAndLanguage(String value, Language language) throws SQLException;

    List<Word> findByLanguage(Language language) throws SQLException;

    Collection<Word> findAll() throws SQLException;


    //-------------- remove ---------------//

    void remove(Integer wordID) throws SQLException;
}
