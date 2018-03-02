package com.sschudakov.service;

import com.sschudakov.dao.impl.jdbc.LanguageDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WCRDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WMRDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordClassDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordDaoJdbcImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class WordSrv {

    private WordDao wordDao;
    private LanguageDao languageDao;
    private WordClassDao wordClassDao;
    private WMRDao wmrDao;
    private WCRDao wcrDao;

    public WordSrv() {
        this.wordDao = new WordDaoJdbcImpl();
        this.languageDao = new LanguageDaoJdbcImpl();
        this.wordClassDao = new WordClassDaoJdbcImpl();
        this.wmrDao = new WMRDaoJdbcImpl();
        this.wcrDao = new WCRDaoJdbcImpl();
    }


    public void create(String wordValue, String wordClass, String language) throws SQLException {
        Word word = new Word();
        word.setValue(wordValue);
        word.setLanguage(this.languageDao.findByName(language));
        System.out.println("Word service: word class: " + wordClass);
        if (wordClass != null) {
            word.setWordClass(this.wordClassDao.findByName(wordClass));
        } else {
            word.setWordClass(new WordClass());//TODO: fix this
        }
        this.wordDao.save(word);
    }


    public Word update(Word word) throws SQLException {
        return this.wordDao.update(word);
    }


    public Word findById(Integer id) throws SQLException {
        return this.wordDao.findById(id);
    }


    public List<Word> findByLanguage(String languageName) throws SQLException {
        return this.wordDao.findByLanguageId(this.languageDao.findByName(languageName).getId());
    }


    public Word findByValueAndLanguage(String value, String languageName) throws SQLException {
        Language foundLanguage = this.languageDao.findByName(languageName);
        return this.wordDao.findByValueAndLanguageId(value, foundLanguage.getId());
    }


    public Collection<Word> findAll() throws SQLException {
        return this.wordDao.findAll();
    }


    public void delete(Integer wordId) throws SQLException {
        this.wordDao.remove(wordId);
        this.wmrDao.removeAllWordRelationships(wordId);
        this.wcrDao.removeByWordId(wordId);
    }
}
