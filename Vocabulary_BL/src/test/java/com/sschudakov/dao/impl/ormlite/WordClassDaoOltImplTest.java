package com.sschudakov.dao.impl.ormlite;

import com.sschudakov.dao.impl.ormlite.WordClassDaoImpl;
import com.sschudakov.entity.WordClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Collection;

public class WordClassDaoImplTest {

    private WordClassDaoImpl wordClassDao;

    @Before
    public void init() {
        this.wordClassDao = new WordClassDaoImpl();
    }

    @Test
    public void save() throws SQLException {
        WordClass wordClass = new WordClass();
        wordClass.setWordClassName("adverb");
        this.wordClassDao.save(wordClass);
    }

    @Test
    public void findById() throws SQLException {
        WordClass foundWordClass = this.wordClassDao.findById(1);
        System.out.println("found word class: " + foundWordClass);
    }

    @Test
    public void findByName() throws SQLException {
        WordClass wordClass = this.wordClassDao.findByName("adverb");
        System.out.println("found word class: " + wordClass);
    }

    @Test
    public void findAll() throws SQLException {
        Collection<WordClass> wordClasses = this.wordClassDao.findAll();
        for (WordClass wordClass : wordClasses) {
            System.out.println(wordClass);
        }
    }
}