package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.ormlite.WordClassDaoOltImpl;
import com.sschudakov.dao.interf.WordClassDao;
import com.sschudakov.entity.WordClass;
import com.sschudakov.service.interf.WordClassSrv;

import java.sql.SQLException;

public class WordClassSrvImpl implements WordClassSrv{

    private WordClassDao wordClassDao;

    public WordClassSrvImpl() {
        this.wordClassDao = new WordClassDaoOltImpl();
    }

    @Override
    public void create(String wordClassName) throws SQLException {
        this.wordClassDao.save(new WordClass(wordClassName));
    }

}
