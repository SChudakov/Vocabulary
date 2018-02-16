package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.ormlite.LanguageDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WCRDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WordCollectionDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WordDaoOltImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.service.interf.WCRSrv;

import java.sql.SQLException;
import java.util.Collection;

public class WCRSrvImpl implements WCRSrv {

    private WCRDao wcrDao;
    private LanguageDao languageDao;
    private WordCollectionDao wordCollectionDao;
    private WordDao wordDao;

    public WCRSrvImpl() {
        this.wcrDao = new WCRDaoOltImpl();
        this.languageDao = new LanguageDaoOltImpl();
        this.wordCollectionDao = new WordCollectionDaoOltImpl();
        this.wordDao = new WordDaoOltImpl();
    }

    @Override
    public void create(Word word, WordCollection wordCollection) throws SQLException {
        this.wcrDao.save(new WordCollectionRelationship(word, wordCollection));
    }

    @Override
    public void delete(Integer wcrId) throws SQLException {
        this.wcrDao.remove(wcrId);
    }

    @Override
    public WordCollectionRelationship update(WordCollectionRelationship wcr) throws SQLException {
        return this.wcrDao.update(wcr);
    }

    @Override
    public Collection<WordCollectionRelationship> findByWordAndLanguage(String word, String language) throws SQLException {
        Language foundLanguage = this.languageDao.findByName(language);
        Word foundWord = this.wordDao.findByValueAndLanguage(word, foundLanguage);
        return this.wcrDao.findByWordId(foundWord.getWordID());
    }

    @Override
    public Collection<WordCollectionRelationship> findByCollection(String collection) throws SQLException {
        WordCollection foundCollection = this.wordCollectionDao.findByName(collection);
        return this.wcrDao.findByCollectionId(foundCollection.getCollectionID());
    }
}