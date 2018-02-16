package com.sschudakov.dao.interf;

import com.sschudakov.entity.Language;

import java.sql.SQLException;
import java.util.List;

public interface LanguageDao {
    void save(Language language) throws SQLException;

    Language findById(Integer id) throws SQLException;

    Language findByName(String name) throws SQLException;

    List<Language> findAll() throws SQLException;
}
