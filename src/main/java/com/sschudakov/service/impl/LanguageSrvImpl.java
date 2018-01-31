package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.LanguageDaoImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.entity.Language;
import com.sschudakov.service.interf.LanguageSrv;

import java.sql.SQLException;

public class LanguageSrvImpl implements LanguageSrv {

    private LanguageDao languageDao;


    public LanguageSrvImpl() {
        this.languageDao = new LanguageDaoImpl();
    }


    @Override
    public void create(String languageName) throws SQLException {
        this.languageDao.save(new Language(languageName));
    }

    @Override
    public void delete(Integer languageId) throws SQLException {
        this.languageDao.remove(languageId);
    }

    @Override
    public Language update(Language language) throws SQLException {
        return this.languageDao.update(language);
    }
}
