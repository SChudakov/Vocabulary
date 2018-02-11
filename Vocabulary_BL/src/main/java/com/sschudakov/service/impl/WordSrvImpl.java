package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.ormlite.LanguageDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WCRDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WMRDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WordClassDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WordCollectionDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WordDaoOltImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.entity.WordMeaningRelationship;
import com.sschudakov.service.interf.WordSrv;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class WordSrvImpl implements WordSrv {

    private WordDao wordDao;
    private LanguageDao languageDao;
    private WordClassDao wordClassDao;
    private WordCollectionDao wordCollectionDao;
    private WMRDao wmrDao;
    private WCRDao wcrDao;


    public WordSrvImpl() {
        this.wordDao = new WordDaoOltImpl();
        this.languageDao = new LanguageDaoOltImpl();
        this.wordClassDao = new WordClassDaoOltImpl();
        this.wordCollectionDao = new WordCollectionDaoOltImpl();
        this.wmrDao = new WMRDaoOltImpl();
        this.wcrDao = new WCRDaoOltImpl();
    }

    @Override
    public void create(String wordValue, String wordClass, String language,
                       Collection<String> collectionsNames, Collection<String> meanings,
                       String meaningsLanguage) throws SQLException {
        Word word = new Word();
        word.setValue(wordValue);
        word.setLanguage(this.languageDao.findByName(language));
        word.setWordClass(this.wordClassDao.findByName(wordClass));
        this.wordDao.save(word);

        for (String collectionsName : collectionsNames) {
            this.wcrDao.save(new WordCollectionRelationship(
                    word,
                    this.wordCollectionDao.findByName(collectionsName)
            ));
        }
        Language meanLng = this.languageDao.findByName(meaningsLanguage);
        for (String meaning : meanings) {

            this.wmrDao.save(new WordMeaningRelationship(
                    word,
                    this.wordDao.findByValueAndLanguage(meaning, meanLng)
            ));
        }
        throw new UnsupportedOperationException();
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
    public List<Word> findAll() throws SQLException {
        return this.wordDao.findAll();
    }
}
