package com.sschudakov.dao.impl.hibernate;

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
    }

    @Test
    public void findById() throws SQLException {
        System.out.println(this.wordClassDaoHbn.findById(2));
        this.wordClassDaoHbn.closeEntityManager();
        this.wordClassDaoHbn.openEntityManager();
        System.out.println(this.wordClassDaoHbn.findById(2));
    }

    @Test
    public void findByName() throws SQLException {
        System.out.println(this.wordClassDaoHbn.findByName("adverb"));
        System.out.println(this.wordClassDaoHbn.findByName("adverb"));
        this.wordClassDaoHbn.getEntityManager().close();
        this.wordClassDaoHbn.getEntityManagerFactory().close();
    }

    @Test
    public void findAll() {
    }
}