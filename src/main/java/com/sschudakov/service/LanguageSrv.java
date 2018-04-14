package com.sschudakov.service;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageSrv {


    //-------------- dao object  ---------------//
    @Autowired
    private LanguageDao languageDao;


    //-------------- constructor  ---------------//

    public LanguageSrv(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }


    //-------------- create  ---------------//

    public void create(String languageName) throws SQLException {
        if (languageExists(languageName)) {
            throw new IllegalArgumentException("There is already a language with the name " + languageName);
        } else {
            this.languageDao.save(new Language(languageName));
        }
    }


    //-------------- find ---------------//

    public Language findById(Integer id) throws SQLException {
        return this.languageDao.findById(id);
    }


    public Language findByName(String name) throws SQLException {
        return this.languageDao.findByName(name);
    }


    public List<String> findAll() throws SQLException {
        return this.languageDao.findAll().stream().map(Language::getLanguageName).collect(Collectors.toList());
    }

    public List<Language> findAllObjects() throws SQLException {
        return this.languageDao.findAll();
    }


    //-------------- exist query ---------------//

    private boolean languageExists(String languageName) throws SQLException {
        return this.languageDao.findByName(languageName) != null;
    }
}
