package com.sschudakov.service;

import com.sschudakov.dao.impl.jdbc.WordClassDaoJdbcImpl;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.entity.WordClass;

import java.sql.SQLException;
import java.util.List;

public class WordClassSrv {

    private WordClassDao wordClassDao;

    public WordClassSrv() {
        this.wordClassDao = new WordClassDaoJdbcImpl();
    }


    public void create(String wordClassName) throws SQLException {
        this.wordClassDao.save(new WordClass(wordClassName));
    }


    public WordClass findById(Integer id) throws SQLException {
        return this.wordClassDao.findById(id);
    }


    public WordClass findByName(String name) throws SQLException {
        return this.wordClassDao.findByName(name);
    }


    public List<WordClass> findAll() throws SQLException {
        return this.wordClassDao.findAll();
    }

}
