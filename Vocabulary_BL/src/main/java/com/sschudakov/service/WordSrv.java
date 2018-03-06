package com.sschudakov.service;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.entity.WordMeaningRelationship;
import com.sschudakov.factory.DaoFactory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WordSrv {

    private WordDao wordDao;
    private LanguageDao languageDao;
    private WordClassDao wordClassDao;
    private WMRDao wmrDao;
    private WCRDao wcrDao;

    public WordSrv() {
        this.wordDao = DaoFactory.createWordDao();
        this.languageDao = DaoFactory.createLanguageDao();
        this.wordClassDao = DaoFactory.createWordClassDao();
        this.wmrDao = DaoFactory.createWMRDao();
        this.wcrDao = DaoFactory.createWCRDao();
    }


    public void create(String wordValue, String wordClass, String language) throws SQLException {
        Word word = new Word();
        word.setValue(wordValue);
        word.setLanguage(this.languageDao.findByName(language));
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


    public List<String> findByLanguage(String languageName) throws SQLException {
        return this.wordDao.findByLanguage(this.languageDao.findByName(languageName))
                .stream().map(Word::getValue).collect(Collectors.toList());
    }


    public Word findByValueAndLanguage(String value, String languageName) throws SQLException {
        Language foundLanguage = this.languageDao.findByName(languageName);
        return this.wordDao.findByValueAndLanguage(value, foundLanguage);
    }


    public Collection<Word> findAll() throws SQLException {
        return this.wordDao.findAll();
    }


    public void delete(Integer wordId) throws SQLException {
        Word foundWord = wordDao.findById(wordId);
        for (WordCollectionRelationship wordCollectionRelationship : wcrDao.findByWord(foundWord)) {
            this.wcrDao.remove(wordCollectionRelationship.getId());
        }
        for (WordMeaningRelationship wordMeaningRelationship : wmrDao.findByWord(foundWord)) {
            this.wmrDao.remove(wordMeaningRelationship.getId());
        }
        for (WordMeaningRelationship wordMeaningRelationship : wmrDao.findByMeaning(foundWord)) {
            this.wmrDao.remove(wordMeaningRelationship.getId());
        }
        this.wordDao.remove(wordId);
    }
}
