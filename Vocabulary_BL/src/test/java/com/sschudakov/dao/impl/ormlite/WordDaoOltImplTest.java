package com.sschudakov.dao.impl.ormlite;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class WordDaoOltImplTest {

    private WordDaoOltImpl wordDaoOlt;

    @Before
    public void init() {
        this.wordDaoOlt = new WordDaoOltImpl();
    }

    @Test
    public void save() throws SQLException {
        Word word = new Word();
        word.setValue("apple");
        word.setWordClass(new WordClass("noun"));
        word.setLanguage(new Language("English"));

        this.wordDaoOlt.save(word);
    }

    @Test
    public void update() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByValueAndLanguage() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void remove() {
    }
}