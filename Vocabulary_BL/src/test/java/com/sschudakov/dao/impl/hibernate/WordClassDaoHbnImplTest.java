package com.sschudakov.dao.impl.hibernate;

import com.sschudakov.entity.WordClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class WordClassDaoHbnImplTest {

    private WordClassDaoHbnImpl wordClassDaoHbn;

    @Before
    public void init() {
        this.wordClassDaoHbn = new WordClassDaoHbnImpl();
    }


    @Test
    public void save() {
        this.wordClassDaoHbn.save(new WordClass("test word class"));
    }

    @Test
    public void findById() throws SQLException {
        System.out.println(this.wordClassDaoHbn.findById(10011));
    }

    @Test
    public void findByName() throws SQLException {
        System.out.println(this.wordClassDaoHbn.findByName("verb"));
    }

    @Test
    public void findAll() {
        for (WordClass wordClass : this.wordClassDaoHbn.findAll()) {
            System.out.println(wordClass);
        }
    }
}