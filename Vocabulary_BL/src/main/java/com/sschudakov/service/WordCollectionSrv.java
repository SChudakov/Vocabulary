package com.sschudakov.service;

import com.sschudakov.dao.impl.jdbc.WCRDaoJdbcImpl;
import com.sschudakov.dao.impl.jdbc.WordCollectionDaoJdbcImpl;
import com.sschudakov.dao.interf.WCRDao;
import com.sschudakov.dao.interf.WordCollectionDao;
import com.sschudakov.entity.WordCollection;
import com.sschudakov.entity.WordCollectionRelationship;
import com.sschudakov.factory.DaoFactory;

import java.sql.SQLException;
import java.util.List;

public class WordCollectionSrv {

    private WordCollectionDao wordCollectionDao;
    private WCRDao wcrDao;

    public WordCollectionSrv() {
        this.wordCollectionDao = DaoFactory.createWordCollection();
        this.wcrDao = DaoFactory.createWCRDao();
    }


    public void create(String wordCollectionName) throws SQLException {
        this.wordCollectionDao.save(new WordCollection(wordCollectionName));
    }


    public WordCollection update(WordCollection wordCollection) throws SQLException {
        return this.wordCollectionDao.update(wordCollection);
    }


    public WordCollection findById(Integer id) throws SQLException {
        return this.wordCollectionDao.findById(id);
    }


    public WordCollection findByName(String name) throws SQLException {
        return this.wordCollectionDao.findByName(name);
    }


    public List<WordCollection> findAll() throws SQLException {
        return this.wordCollectionDao.findAll();
    }


    public void deleteByName(String name) throws SQLException {
        delete(this.wordCollectionDao.findByName(name).getId());
    }


    public void delete(Integer collectionsId) throws SQLException {
        for (WordCollectionRelationship wordCollectionRelationship : this.wcrDao.findByCollection(
                wordCollectionDao.findById(collectionsId)
        )) {
            this.wcrDao.remove(wordCollectionRelationship.getId());
        }
        this.wordCollectionDao.remove(collectionsId);
    }
}
