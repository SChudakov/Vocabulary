package com.sschudakov.dao.impl.jdbc;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class WordDaoJdbcImplTest {

    private WordDaoJdbcImpl wordDaoOlt;
    private Word word;

    @Before
    public void init() {
        this.wordDaoOlt = new WordDaoJdbcImpl();
        WordClass wordClass = new WordClass("noun");
        wordClass.setId(0);
        Language language = new Language("English");
        language.setId(0);
        this.word = new Word();
        word.setValue("apple");
        word.setWordClass(wordClass);
        word.setLanguage(language);
        this.word.setWordID(4);
    }

    @Test
    public void save() throws SQLException {
        this.wordDaoOlt.save(word);
    }

    @Test
    public void update() throws SQLException {
        this.word.setValue("cat");
        this.wordDaoOlt.update(word);
    }

    @Test
    public void findById() throws SQLException {
        Word foundWord = this.wordDaoOlt.findById(4);
        System.out.println("found word" + foundWord);
    }

    @Test
    public void findByValueAndLanguageId() throws SQLException {
        Language language = new Language("English");
        language.setId(0);
        String value = "cat";
        Word foundWord = this.wordDaoOlt.findByValueAndLanguageId(value, language.getId());
        System.out.println("found word" + foundWord);
    }

    @Test
    public void findAll() throws SQLException {
        System.out.println("found words");
        for (Word word1 : this.wordDaoOlt.findAll()) {
            System.out.println(word1);
        }
    }

    @Test
    public void remove() throws SQLException {
        this.wordDaoOlt.remove(4);
    }
}