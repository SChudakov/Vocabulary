package com.sschudakov.service.interf;

import com.sschudakov.entity.Language;

import java.sql.SQLException;

public interface LanguageSrv {

    void create(String languageName) throws SQLException;

}
