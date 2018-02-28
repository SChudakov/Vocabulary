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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        this.wmrDao.save(new WordMeaningRelationship(meaning, word));
    }


    @Override
    public WordMeaningRelationship update(WordMeaningRelationship wmr) throws SQLException {
        return this.wmrDao.update(wmr);
    }


    @Override
    public WordMeaningRelationship findById(Integer id) throws SQLException {
        return this.wmrDao.findById(id);
    }

    @Override
    public Collection<WordMeaningRelationship> findByWordAndLanguage(String word, String language) throws SQLException {
        Language foundLanguage = this.languageDao.findByName(language);
        Word foundWord = this.wordDao.findByValueAndLanguageId(word, foundLanguage.getId());
        return this.wmrDao.findByWordId(foundWord.getWordID());
    }

    @Override
    public Collection<Word> findWordMeanings(String word, String wordLanguage, String meaningsLanguage) throws SQLException {
        Collection<Word> result = new ArrayList<>();

        Language foundWordLanguage = this.languageDao.findByName(wordLanguage);
        Language foundMeaningLanguage = this.languageDao.findByName(meaningsLanguage);
        Word foundWord = this.wordDao.findByValueAndLanguageId(word, foundWordLanguage.getId());

        for (int meaningId : this.wmrDao.findMeaningsIds(foundWord.getWordID(), foundMeaningLanguage.getId())) {
            result.add(this.wordDao.findById(meaningId));
        }

        return result;
    }

    @Override
    public Collection<WordMeaningRelationship> findByWordAndMeaning(String word, String language, String meaning, String meaningLanguage) throws SQLException {
        Word foundWord = this.wordDao.findByValueAndLanguageId(word, this.languageDao.findByName(language).getId());
        Word foundMeaning = this.wordDao.findByValueAndLanguageId(meaning, this.languageDao.findByName(meaningLanguage).getId());
        return this.wmrDao.findByWordAndMeaningIds(foundWord.getWordID(), foundMeaning.getWordID());
    }


    @Override
    public List<WordMeaningRelationship> findAll() throws SQLException {
        return this.wmrDao.findAll();
    }


    @Override
    public void delete(String word, String wordsLanguage, String meaning, String meaningsLanguage) throws SQLException {
        Word foundWord = this.wordDao.findByValueAndLanguageId(word, this.languageDao.findByName(wordsLanguage).getId());
        Word foundMeaning = this.wordDao.findByValueAndLanguageId(meaning, this.languageDao.findByName(meaningsLanguage).getId());
        this.wmrDao.remove(foundWord.getWordID(), foundMeaning.getWordID());
    }

    @Override
    public void delete(Integer wmrId) throws SQLException {
        this.wmrDao.remove(wmrId);
    }
}
