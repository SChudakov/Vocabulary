package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.ormlite.WordClassDaoOltImpl;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.entity.WordClass;
import com.sschudakov.service.interf.WordClassSrv;

import java.sql.SQLException;
import java.util.List;

public class WordClassSrvImpl implements WordClassSrv{

    private WordClassDao wordClassDao;

    public WordClassSrvImpl() {
        this.wordClassDao = new WordClassDaoOltImpl();
    }

    @Override
    public void create(String wordClassName) throws SQLException {
        this.wordClassDao.save(new WordClass(wordClassName));
    }

    @Override
    public WordClass findById(Integer id) throws SQLException {
        return this.wordClassDao.findById(id);
    }

    @Override
    public WordClass findByName(String name) throws SQLException {
        return this.wordClassDao.findByName(name);
    }

    @Override
    public List<WordClass> findAll() throws SQLException {
        return this.wordClassDao.findAll();
    }

}
