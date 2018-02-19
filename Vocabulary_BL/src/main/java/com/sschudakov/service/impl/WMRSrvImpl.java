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
    }

    @Override
    public WordMeaningRelationship findById(Integer id) throws SQLException {
        return this.wmrDao.findById(id);
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
    public List<WordMeaningRelationship> findAll() throws SQLException {
        return this.wmrDao.findAll();
    }

    @Override
    public void delete(Integer wmrId) throws SQLException {
        this.wmrDao.remove(wmrId);
    }

    @Override
    public void delete(String word, String wordsLanguage, String meaning, String meaningsLanugage) throws SQLException {
        Word foundWord = this.wordDao.findByValueAndLanguage(word, this.languageDao.findByName(wordsLanguage));
        Word foundMeaning = this.wordDao.findByValueAndLanguage(meaning, this.languageDao.findByName(meaningsLanugage));
        this.wmrDao.remove(foundWord.getWordID(), foundMeaning.getWordID());
    }

    @Override
    public WordMeaningRelationship update(WordMeaningRelationship wmr) throws SQLException {
        return this.wmrDao.update(wmr);
    }

    @Override
    public Collection<Word> findWordMeanings(String word, String wordLanguage, String meaningsLanguage) throws SQLException {
        Collection<Word> result = new ArrayList<>();

        Language foundWordLanguage = this.languageDao.findByName(wordLanguage);
        Language foundMeaningLanguage = this.languageDao.findByName(meaningsLanguage);
        Word foundWord = this.wordDao.findByValueAndLanguage(word, foundWordLanguage);

        System.out.println("word language: " + foundWordLanguage);
        System.out.println("word id: " + foundMeaningLanguage);
        System.out.println("word id" + foundWord.getValue());


        for (int meaningId : this.wmrDao.findWordMeaningsIds(foundWord.getWordID(), foundMeaningLanguage.getLanguageID())) {
            result.add(this.wordDao.findById(meaningId));
        }
        return result;
    }
}
