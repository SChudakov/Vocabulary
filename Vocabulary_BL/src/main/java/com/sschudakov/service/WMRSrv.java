package com.sschudakov.service;

import com.sschudakov.dao.interf.LanguageDao;
import com.sschudakov.dao.interf.WMRDao;
import com.sschudakov.dao.interf.WordDao;
import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordMeaningRelationship;
import com.sschudakov.factory.DaoFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WMRSrv {

    private WMRDao wmrDao;
    private WordDao wordDao;
    private LanguageDao languageDao;

    public WMRSrv() {
        this.wmrDao = DaoFactory.createWMRDao();
        this.wordDao = DaoFactory.createWordDao();
        this.languageDao = DaoFactory.createLanguageDao();
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
        Word foundWord = this.wordDao.findByValueAndLanguage(word, foundLanguage);
        return this.wmrDao.findByWord(foundWord);
    }


    public Collection<Word> findWordMeanings(String word, String wordLanguage, String meaningsLanguage) throws SQLException {
        Collection<Word> result = new ArrayList<>();

        Language foundWordLanguage = this.languageDao.findByName(wordLanguage);
        Language foundMeaningLanguage = this.languageDao.findByName(meaningsLanguage);
        Word foundWord = this.wordDao.findByValueAndLanguage(word, foundWordLanguage);

        for (int meaningId : this.wmrDao.findWordMeaningsIds(foundWord, foundMeaningLanguage)) {
            result.add(this.wordDao.findById(meaningId));
        }

        return result;
    }


    public WordMeaningRelationship findByWordAndMeaning(String word, String language, String meaning, String meaningLanguage) throws SQLException {
        Word foundWord = this.wordDao.findByValueAndLanguage(word, this.languageDao.findByName(language));
        Word foundMeaning = this.wordDao.findByValueAndLanguage(meaning, this.languageDao.findByName(meaningLanguage));
        return this.wmrDao.findByWordAndMeaning(foundWord, foundMeaning);
    }


    public List<WordMeaningRelationship> findAll() throws SQLException {
        return this.wmrDao.findAll();
    }


    public void delete(String word, String wordsLanguage, String meaning, String meaningsLanguage) throws SQLException {
        Word foundWord = this.wordDao.findByValueAndLanguage(word, this.languageDao.findByName(wordsLanguage));
        Word foundMeaning = this.wordDao.findByValueAndLanguage(meaning, this.languageDao.findByName(meaningsLanguage));
        this.wmrDao.remove(this.wmrDao.findByWordAndMeaning(foundWord, foundMeaning).getId());
        this.wmrDao.remove(this.wmrDao.findByWordAndMeaning(foundMeaning, foundWord).getId());
    }


    public void delete(Integer wmrId) throws SQLException {
        this.wmrDao.remove(wmrId);
    }
}
