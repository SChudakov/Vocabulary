package com.sschudakov.service.impl;

import com.sschudakov.dao.impl.ormlite.WCRDaoOltImpl;
import com.sschudakov.dao.impl.ormlite.WordCollectionDaoOltImpl;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.service.interf.WordCollectionSrv;

import java.sql.SQLException;
import java.util.List;

public class WordCollectionSrvImpl implements WordCollectionSrv {

    private WordCollectionDao wordCollectionDao;
    private WCRDao wcrDao;

    public WordCollectionSrvImpl() {
        this.wordCollectionDao = new WordCollectionDaoOltImpl();
        this.wcrDao = new WCRDaoOltImpl();
    }

    @Override
    public void create(String wordCollectionName) throws SQLException {
        this.wordCollectionDao.save(new WordCollection(wordCollectionName));
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

    @Override
    public void deleteByName(String name) throws SQLException {
        delete(this.wordCollectionDao.findByName(name).getId());
    }

    @Override
    public void delete(Integer collectionsId) throws SQLException {
        this.wordCollectionDao.remove(collectionsId);
        this.wcrDao.removeByCollectionId(collectionsId);
    }
}
