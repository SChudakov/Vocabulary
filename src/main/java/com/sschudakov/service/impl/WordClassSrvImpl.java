package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.WordClassDaoImpl;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.entity.WordClass;
import com.sschudakov.service.interf.WordClassSrv;

import java.sql.SQLException;

public class WordClassSrvImpl implements WordClassSrv{

    private WordClassDao wordClassDao;

    public WordClassSrvImpl() {
        this.wordClassDao = new WordClassDaoImpl();
    }

    @Override
    public void create(String wordClassName) throws SQLException {
        this.wordClassDao.save(new WordClass(wordClassName));
    }

    @Override
    public void delete(Integer wordClassId) throws SQLException {
        this.wordClassDao.remove(wordClassId);
    }

    @Override
    public WordClass update(WordClass wordClass) throws SQLException {
        return this.wordClassDao.update(wordClass);
    }
}
