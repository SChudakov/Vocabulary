package com.sschudakov.dao.impl.ormlite;

import com.sschudakov.entity.WordCollection;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Collection;

public class WordCollectionDaoOltImplTest {

    private WordCollectionDaoOltImpl wordCollectionDao;

    @Before
    public void init() {
        this.wordCollectionDao = new WordCollectionDaoOltImpl();
    }

    @Test
    public void save() throws SQLException {
        WordCollection wordCollection = new WordCollection();
        wordCollection.setCollectionName("some collection");
        this.wordCollectionDao.save(wordCollection);
    }

    @Test
    public void update() {
        /*WordCollection wordCollection = new WordCollection();
        wordCollection.setCollectionName("some collection");
        this.wordCollectionDao.update(wordCollection);*/
    }

    @Test
    public void findById() throws SQLException {
        WordCollection foundLanguage = this.wordCollectionDao.findById(1);
        System.out.println("found word collection: " + foundLanguage);
    }

    @Test
    public void findByName() throws SQLException {
        WordCollection wordCollection;
        wordCollection = this.wordCollectionDao.findByName("some collection");
        System.out.println("found word collection: " + wordCollection);
    }

    @Test
    public void findAll() throws SQLException {
        Collection<WordCollection> wordCollections =  this.wordCollectionDao.findAll();
        for (WordCollection wordCollection : wordCollections) {
            System.out.println(wordCollection);
        }
    }

    @Test
    public void remove() {
    }
}