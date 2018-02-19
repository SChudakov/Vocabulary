package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.ormlite.WordCollectionDaoOltImpl;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.service.interf.WordCollectionSrv;

import java.sql.SQLException;
import java.util.List;

public class WordCollectionSrvImpl implements WordCollectionSrv {

    private WordCollectionDao wordCollectionDao;

    public WordCollectionSrvImpl() {
        this.wordCollectionDao = new WordCollectionDaoOltImpl();
    }

    @Override
    public void create(String wordCollectionName) throws SQLException {
        this.wordCollectionDao.save(new WordCollection(wordCollectionName));
    }

    @Override
    public void delete(String name) throws SQLException {
        this.wordCollectionDao.remove(this.wordCollectionDao.findByName(name).getCollectionID());
    }

    @Override
    public WordCollection update(WordCollection wordCollection) throws SQLException {
        return this.wordCollectionDao.update(wordCollection);
    }

    @Override
    public WordCollection findById(Integer id) throws SQLException {
        return this.wordCollectionDao.findById(id);
    }

    @Override
    public WordCollection findByName(String name) throws SQLException {
        return this.wordCollectionDao.findByName(name);
    }

    @Override
    public List<WordCollection> findAll() throws SQLException {
        return this.wordCollectionDao.findAll();
    }
}
