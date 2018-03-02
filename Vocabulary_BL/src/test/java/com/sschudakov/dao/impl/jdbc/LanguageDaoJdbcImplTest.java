package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.entity.Language;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Collection;

public class LanguageDaoJdbcImplTest {

    private LanguageDaoJdbcImpl languageDao;

    @Before
    public void init() {
        this.languageDao = new LanguageDaoJdbcImpl();
    }

    @Test
    public void save() throws SQLException {
        Language language = new Language();
        language.setLanguageName("English");
        this.languageDao.save(language);
    }

    @Test
    public void findById() throws SQLException {
        Language foundLanguage = this.languageDao.findById(1);
        System.out.println("found language: " + foundLanguage);
    }

    @Test
    public void findByName() throws SQLException {
        Language foundLanguage;
        foundLanguage = this.languageDao.findByName("English");
        System.out.println("found language: " + foundLanguage);
    }

    @Test
    public void findAll() throws SQLException {
        Collection<Language> languages =  this.languageDao.findAll();
        for (Language language : languages) {
            System.out.println(language);
        }
    }
}