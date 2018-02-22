package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.ormlite.LanguageDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WCRDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WMRDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WordClassDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WordDaoOltImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.service.interf.WordSrv;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class WordSrvImpl implements WordSrv {

    private WordDao wordDao;
    private LanguageDao languageDao;
    private WordClassDao wordClassDao;
    private WMRDao wmrDao;
    private WCRDao wcrDao;

    public WordSrvImpl() {
        this.wordDao = new WordDaoOltImpl();
        this.languageDao = new LanguageDaoOltImpl();
        this.wordClassDao = new WordClassDaoOltImpl();
        this.wmrDao = new WMRDaoOltImpl();
        this.wcrDao = new WCRDaoOltImpl();
    }

    @Override
    public void create(String wordValue, String wordClass, String language) throws SQLException {
        Word word = new Word();
        word.setValue(wordValue);
        word.setLanguage(this.languageDao.findByName(language));
        word.setWordClass(this.wordClassDao.findByName(wordClass));
        this.wordDao.save(word);
    }

    @Override
    public void delete(Integer wordId) throws SQLException {
        this.wordDao.remove(wordId);
    }

    @Override
    public Word update(Word word) throws SQLException {
        return this.wordDao.update(word);
    }

    @Override
    public Word findById(Integer id) throws SQLException {
        return this.wordDao.findById(id);
    }

    @Override
    public Word findByValueAndLanguage(String value, String languageName) throws SQLException {
        Language foundLanguage = this.languageDao.findByName(languageName);
        return this.wordDao.findByValueAndLanguage(value, foundLanguage);
    }

    @Override
    public Collection<Word> findAll() throws SQLException {
        return this.wordDao.findAll();
    }

    @Override
    public List<Word> findByLanguage(String languageName) throws SQLException {
        return this.wordDao.findByLanguage(this.languageDao.findByName(languageName));
    }
}
