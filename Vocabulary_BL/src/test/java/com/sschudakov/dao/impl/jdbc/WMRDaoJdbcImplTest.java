package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordMeaningRelationship;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class WMRDaoJdbcImplTest {

    private WMRDaoJdbcImpl wmrDaoOlt;
    private WordMeaningRelationship wordMeaningRelationship;
    private Word word;
    private Word meaning;

    @Before
    public void init() {
        this.wmrDaoOlt = new WMRDaoJdbcImpl();
        WordClass wordClass = new WordClass("noun");
        wordClass.setId(0);
        Language language = new Language("English");
        language.setId(0);
        this.word = new Word();
        this.word.setValue("apple");
        this.word.setWordClass(wordClass);
        this.word.setLanguage(language);
        this.word.setWordID(0);
        this.meaning = new Word();
        this.meaning.setValue("carrot");
        this.meaning.setWordClass(wordClass);
        this.meaning.setLanguage(language);
        this.meaning.setWordID(1);
        this.wordMeaningRelationship = new WordMeaningRelationship();
        this.wordMeaningRelationship.setWord(this.word);
        this.wordMeaningRelationship.setMeaning(this.meaning);
        this.wordMeaningRelationship.setId(0);
    }

    @Test
    public void save() throws SQLException {
        this.wmrDaoOlt.save(this.wordMeaningRelationship);
    }

    @Test
    public void update() throws SQLException {
        this.wmrDaoOlt.update(this.wordMeaningRelationship);
    }

    @Test
    public void findById() throws SQLException {
        System.out.println(this.wmrDaoOlt.findById(0));
    }

    @Test
    public void findByWordId() throws SQLException {
        for (WordMeaningRelationship meaningRelationship : this.wmrDaoOlt.findByWordId(0)) {
            System.out.println(meaningRelationship);
        }
    }

    @Test
    public void findAll() throws SQLException {
        for (WordMeaningRelationship meaningRelationship : this.wmrDaoOlt.findAll()) {
            System.out.println(meaningRelationship);
        }
    }

    @Test
    public void remove() throws SQLException {
        this.wmrDaoOlt.remove(this.wordMeaningRelationship.getId());
    }

    @Test
    public void findWordMeaningsIds() throws SQLException {
        for (Integer integer : this.wmrDaoOlt.findMeaningsIds(1, 1)) {
            System.out.println(integer);
        }
    }
}