package com.sschudakov.service;

import com.sschudakov.dao.impl.jdbc.LanguageDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WCRDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordCollectionDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordDaoJdbcImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.factory.DaoFactory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class WCRSrv {

    private WCRDao wcrDao;
    private LanguageDao languageDao;
    private WordCollectionDao wordCollectionDao;
    private WordDao wordDao;

    public WCRSrv() {
        this.wcrDao = DaoFactory.createWCRDao();
        this.languageDao = DaoFactory.createLanguageDao();
        this.wordCollectionDao = DaoFactory.createWordCollection();
        this.wordDao = DaoFactory.createWordDao();
    }


    public void create(Word word, WordCollection wordCollection) throws SQLException {
        this.wcrDao.save(new WordCollectionRelationship(word, wordCollection));
    }


    public WordCollectionRelationship findById(Integer id) throws SQLException {
        return this.wcrDao.findById(id);
    }


    public void delete(Integer wcrId) throws SQLException {
        this.wcrDao.remove(wcrId);
    }


    public void delete(String word, String language, String collectionName) throws SQLException {
        Word foundWord = this.wordDao.findByValueAndLanguage(word, this.languageDao.findByName(language));
        WordCollection foundCollection = this.wordCollectionDao.findByName(collectionName);
        this.wcrDao.remove(this.wcrDao.findByWordAndCollection(foundWord, foundCollection).getId());
    }


    public WordCollectionRelationship update(WordCollectionRelationship wcr) throws SQLException {
        return this.wcrDao.update(wcr);
    }


    public Collection<WordCollectionRelationship> findByWordAndLanguage(String word, String language) throws SQLException {
        Language foundLanguage = this.languageDao.findByName(language);
        Word foundWord = this.wordDao.findByValueAndLanguage(word, foundLanguage);
        return this.wcrDao.findByWord(foundWord);
    }


    public Collection<WordCollectionRelationship> findByCollection(String collection) throws SQLException {
        WordCollection foundCollection = this.wordCollectionDao.findByName(collection);
        return this.wcrDao.findByCollection(foundCollection);
    }


    public WordCollectionRelationship findByWordAndCollection(String word, String language, String collection) throws SQLException {
        Word foundWord = this.wordDao.findByValueAndLanguage(word, this.languageDao.findByName(language));
        WordCollection foundCollection = this.wordCollectionDao.findByName(collection);
        return this.wcrDao.findByWordAndCollection(foundWord, foundCollection);
    }


    public List<WordCollectionRelationship> findAll() throws SQLException {
        return this.wcrDao.findAll();
    }
}
