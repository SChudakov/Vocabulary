package com.sschudakov.service.impl;

import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.service.interf.WordCollectionSrv;

import java.sql.SQLException;

public class WordCollectionSrvImpl implements WordCollectionSrv {

    private WordCollectionDao wordCollectionDao;

    public WordCollectionSrvImpl() {
    }

    @Override
    public void create(String wordCollectionName) throws SQLException {
        this.wordCollectionDao.save(new WordCollection(wordCollectionName));
    }

    @Override
    public void delete(Integer wordCollectionId) throws SQLException {
        this.wordCollectionDao.remove(wordCollectionId);
    }

    @Override
    public WordCollection update(WordCollection wordCollection) throws SQLException {
        return this.wordCollectionDao.update(wordCollection);
    }
}
