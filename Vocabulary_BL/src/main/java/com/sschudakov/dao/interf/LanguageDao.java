package com.sschudakov.dao.interf;

import com.sschudakov.entity.Language;

import java.sql.SQLException;
import java.util.List;

public interface LanguageDao {


    //-------------- save  ---------------//

    void save(Language language) throws SQLException;


    //-------------- find ---------------//

    Language findById(Integer id) throws SQLException;

    Language findByName(String name) throws SQLException;

    List<Language> findAll() throws SQLException;

    /**
     * Data about languages should never been changed or removed.
     * So update and remove operations are not present in this DAO.
     */
}
