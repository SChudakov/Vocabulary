package com.sschudakov.service;

import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.entity.WordClass;
import com.sschudakov.factory.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class WordClassSrv {

    private WordClassDao wordClassDao;

    public WordClassSrv() {
        this.wordClassDao = DaoFactory.createWordClassDao();
    }


    public void create(String wordClassName) throws SQLException {
        if (wordClassExists(wordClassName)) {
            throw new IllegalArgumentException("There is already word class with name " + wordClassName);
        } else {
            this.wordClassDao.save(new WordClass(wordClassName));
        }
    }


    public WordClass findById(Integer id) throws SQLException {
        return this.wordClassDao.findById(id);
    }


    public WordClass findByName(String name) throws SQLException {
        return this.wordClassDao.findByName(name);
    }


    public List<String> findAll() throws SQLException {
        return this.wordClassDao.findAll().stream().map(WordClass::getWordClassName).collect(Collectors.toList());
    }

    private boolean wordClassExists(String wordClassName) throws SQLException {
        return this.wordClassDao.findByName(wordClassName) != null;
    }

}
