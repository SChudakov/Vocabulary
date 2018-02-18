package com.sschudakov.service.interf;

import com.sschudakov.entity.Language;

import java.sql.SQLException;
import java.util.List;

public interface LanguageSrv {

    void create(String languageName) throws SQLException;

    Language findById(Integer id) throws SQLException;

    Language findByName(String name) throws SQLException;

    List<Language> findAll() throws SQLException;
}
