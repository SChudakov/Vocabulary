package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.ormlite.LanguageDaoOltImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.entity.Language;
import com.sschudakov.service.interf.LanguageSrv;

import java.sql.SQLException;

public class LanguageSrvImpl implements LanguageSrv {

    private LanguageDao languageDao;


    public LanguageSrvImpl() {
        this.languageDao = new LanguageDaoOltImpl();
    }


    @Override
    public void create(String languageName) throws SQLException {
        this.languageDao.save(new Language(languageName));
    }

}
