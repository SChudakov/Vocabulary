package com.sschudakov.service;

import com.sschudakov.dao.impl.jdbc.LanguageDaoJdbcImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.entity.Language;
import com.sschudakov.factory.DaoFactory;

import java.sql.SQLException;
import java.util.List;

public class LanguageSrv {

    private LanguageDao languageDao;


    public LanguageSrv() {
        this.languageDao = DaoFactory.createLanguageDao();
    }


    public void create(String languageName) throws SQLException {
        this.languageDao.save(new Language(languageName));
    }


    public Language findById(Integer id) throws SQLException {
        return this.languageDao.findById(id);
    }


    public Language findByName(String name) throws SQLException {
        return this.languageDao.findByName(name);
    }


    public List<Language> findAll() throws SQLException {
        return this.languageDao.findAll();
    }

}
