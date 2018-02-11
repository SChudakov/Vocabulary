package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.ormlite.LanguageDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WMRDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WordDaoOltImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;
import com.sschudakov.service.interf.WMRSrv;

import java.sql.SQLException;
import java.util.Collection;

public class WMRSrvImpl implements WMRSrv {

    private WMRDao wmrDao;
    private WordDao wordDao;
    private LanguageDao languageDao;

    public WMRSrvImpl() {
        this.wmrDao = new WMRDaoOltImpl();
        this.wordDao = new WordDaoOltImpl();
        this.languageDao = new LanguageDaoOltImpl();
    }

    @Override
    public void create(Word word, Word meaning) throws SQLException {
        this.wmrDao.save(new WordMeaningRelationship(word, meaning));
    }

    @Override
    public Collection<WordMeaningRelationship> findByWordAndLanguage(String word, String language) throws SQLException {
        Language foundLanguage = this.languageDao.findByName(language);
        Word foundWord = this.wordDao.findByValueAndLanguage(word, foundLanguage);
        return this.wmrDao.findByWordId(foundWord.getWordID());
    }

    @Override
    public Collection<WordMeaningRelationship> findByMeaningAndLanguage(String meaning, String language) throws SQLException {
        Language foundLanguage = this.languageDao.findByName(language);
        Word foundMeaning = this.wordDao.findByValueAndLanguage(meaning, foundLanguage);
        return this.wmrDao.findByMeaningId(foundMeaning.getWordID());
    }

    @Override
    public void delete(Integer wmrId) throws SQLException {
        this.wmrDao.remove(wmrId);
    }

    @Override
    public WordMeaningRelationship update(WordMeaningRelationship wmr) throws SQLException {
        return this.wmrDao.update(wmr);
    }
}
