package com.sschudakov.service;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.entity.Language;
import com.sschudakov.factory.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageSrv {

    private LanguageDao languageDao;


    public LanguageSrv(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }


    public void create(String languageName) throws SQLException {
        if (languageExists(languageName)) {
            throw new IllegalArgumentException("There is already a language with the name " + languageName);
        } else {
            this.languageDao.save(new Language(languageName));
        }
    }


    public Language findById(Integer id) throws SQLException {
        return this.languageDao.findById(id);
    }


    public Language findByName(String name) throws SQLException {
        return this.languageDao.findByName(name);
    }


    public List<String> findAll() throws SQLException {
        return this.languageDao.findAll().stream().map(Language::getLanguageName).collect(Collectors.toList());
    }

    private boolean languageExists(String languageName) throws SQLException {
        return this.languageDao.findByName(languageName) != null;
    }
}
