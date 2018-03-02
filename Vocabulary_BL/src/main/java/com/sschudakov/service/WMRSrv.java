package com.sschudakov.service;

import com.sschudakov.dao.impl.jdbc.LanguageDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WMRDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordDaoJdbcImpl;
import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WMRSrv {

    private WMRDao wmrDao;
    private WordDao wordDao;
    private LanguageDao languageDao;

    public WMRSrv() {
        this.wmrDao = new WMRDaoJdbcImpl();
        this.wordDao = new WordDaoJdbcImpl();
        this.languageDao = new LanguageDaoJdbcImpl();
    }


    public void create(Word word, Word meaning) throws SQLException {
        this.wmrDao.save(new WordMeaningRelationship(word, meaning));
        this.wmrDao.save(new WordMeaningRelationship(meaning, word));
    }


    public WordMeaningRelationship update(WordMeaningRelationship wmr) throws SQLException {
        return this.wmrDao.update(wmr);
    }


    public WordMeaningRelationship findById(Integer id) throws SQLException {
        return this.wmrDao.findById(id);
    }


    public Collection<WordMeaningRelationship> findByWordAndLanguage(String word, String language) throws SQLException {
        Language foundLanguage = this.languageDao.findByName(language);
        Word foundWord = this.wordDao.findByValueAndLanguageId(word, foundLanguage.getId());
        return this.wmrDao.findByWordId(foundWord.getWordID());
    }


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


    public Collection<WordMeaningRelationship> findByWordAndMeaning(String word, String language, String meaning, String meaningLanguage) throws SQLException {
        Word foundWord = this.wordDao.findByValueAndLanguageId(word, this.languageDao.findByName(language).getId());
        Word foundMeaning = this.wordDao.findByValueAndLanguageId(meaning, this.languageDao.findByName(meaningLanguage).getId());
        return this.wmrDao.findByWordAndMeaningIds(foundWord.getWordID(), foundMeaning.getWordID());
    }


    public List<WordMeaningRelationship> findAll() throws SQLException {
        return this.wmrDao.findAll();
    }


    public void delete(String word, String wordsLanguage, String meaning, String meaningsLanguage) throws SQLException {
        Word foundWord = this.wordDao.findByValueAndLanguageId(word, this.languageDao.findByName(wordsLanguage).getId());
        Word foundMeaning = this.wordDao.findByValueAndLanguageId(meaning, this.languageDao.findByName(meaningsLanguage).getId());
        this.wmrDao.remove(foundWord.getWordID(), foundMeaning.getWordID());
    }


    public void delete(Integer wmrId) throws SQLException {
        this.wmrDao.remove(wmrId);
    }
}
