package com.sschudakov.dao.interf;

import com.sschudakov.entity.WordCollection;

import java.sql.SQLException;
import java.util.List;

public interface WordCollectionDao {


    //-------------- save  ---------------//

    void save(WordCollection wordCollection) throws SQLException;


    //-------------- update ---------------//

    WordCollection update(WordCollection wordCollection) throws SQLException;


    //-------------- find ---------------//

    WordCollection findById(Integer id) throws SQLException;

    WordCollection findByName(String name) throws SQLException;

    List<WordCollection> findAll() throws SQLException;


    //-------------- remove ---------------//

    void remove(Integer wordCollectionID) throws SQLException;
}
