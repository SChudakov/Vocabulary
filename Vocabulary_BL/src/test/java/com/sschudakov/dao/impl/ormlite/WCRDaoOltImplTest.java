package com.sschudakov.dao.impl.ormlite;

import com.sschudakov.entity.Language;
import com.sschudakov.entity.Word;
import com.sschudakov.entity.WordClass;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class WCRDaoOltImplTest {

    private WCRDaoOltImpl wcrDaoOlt;
    private WordCollectionRelationship wordCollectionRelationship;
    private Word word;
    private WordCollection wordCollection;

    @Before
    public void init() {
        this.wcrDaoOlt = new WCRDaoOltImpl();
        WordClass wordClass = new WordClass("noun");
        wordClass.setId(0);
        Language language = new Language("English");
        language.setId(0);
        this.word = new Word();
        this.word.setValue("apple");
        this.word.setWordClass(wordClass);
        this.word.setLanguage(language);
        this.word.setWordID(0);
        this.wordCollection = new WordCollection();
        this.wordCollection.setCollectionName("first collection");
        this.wordCollection.setId(0);
        this.wordCollectionRelationship = new WordCollectionRelationship();
        this.wordCollectionRelationship.setWord(this.word);
        this.wordCollectionRelationship.setWordCollection(this.wordCollection);
        this.wordCollectionRelationship.setId(0);
    }

    @Test
    public void save() throws SQLException {
        this.wcrDaoOlt.save(this.wordCollectionRelationship);
    }

    @Test
    public void update() throws SQLException {
        this.wcrDaoOlt.update(this.wordCollectionRelationship);
    }

    @Test
    public void findById() throws SQLException {
        System.out.println(this.wcrDaoOlt.findById(0));
    }

    @Test
    public void findByWordId() throws SQLException {
        for (WordCollectionRelationship collectionRelationship : this.wcrDaoOlt.findByWordId(0)) {
            System.out.println(collectionRelationship);
        }
    }

    @Test
    public void findByCollectionId() throws SQLException {
        for (WordCollectionRelationship collectionRelationship : this.wcrDaoOlt.findByCollectionId(0)) {
            System.out.println(collectionRelationship);
        }
    }

    @Test
    public void findAll() throws SQLException {
        for (WordCollectionRelationship collectionRelationship : this.wcrDaoOlt.findAll()) {
            System.out.println(collectionRelationship);
        }
    }

    @Test
    public void remove() throws SQLException {
        this.wcrDaoOlt.remove(0);
    }
}