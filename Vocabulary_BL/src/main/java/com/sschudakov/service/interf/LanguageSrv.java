package com.sschudakov.service.interf;

import com.sschudakov.entity.Language;

import java.sql.SQLException;

public interface LanguageSrv {

    void create(String languageName) throws SQLException;

    void delete(Integer languageId) throws SQLException;

    Language update(Language language) throws SQLException;
}
